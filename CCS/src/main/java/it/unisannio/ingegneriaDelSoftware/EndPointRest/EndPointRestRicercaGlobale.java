package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import it.unisannio.ingegneriaDelSoftware.CcsRestApplication;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Beans.Seriale;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.CTT;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.CTTName;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Beans.Sacca;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche.NotificaRisultatiRicerca;
import it.unisannio.ingegneriaDelSoftware.ClientRest.CCSRestClient;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Notifica;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Subject;
import it.unisannio.ingegneriaDelSoftware.SaccheInScadenza.SaccheInScadenzaObserver;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Functional.ConnectionVerifier;
import it.unisannio.ingegneriaDelSoftware.Functional.NotificaSaccaInScadenzaMaker;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Observer;
import it.unisannio.ingegneriaDelSoftware.Searchers.SearcherFactory;
import it.unisannio.ingegneriaDelSoftware.Util.Settings;

@Path("/CCS")
@Singleton
public class EndPointRestRicercaGlobale implements Subject {

	Observer scadenzeObserver = new SaccheInScadenzaObserver();
	MongoDataManager mm = MongoDataManager.getInstance();

	/**Restituisce la Sacca del GruppoSanguigno richiesto con Data di scadenza più vicina nel DataBase locale.
	 * @param nome Nome del CTT richiedente
	 * @param gruppoSanguigno Gruppo sanguigno ricercato
	 * @param numeroSacche il numero di sacche richieste
	 * @param dataArrivoMassima Data entro la quale la Sacca non deve scadere e deve arrivare all'Ente richiedente
	 * @param enteRichiedente Ente richiedente della Sacca
	 * @param indirizzoEnte Indirizzo dell'ente richiedente
	 * @param priorita Ricerca prioritaria(true), ricerca non prioritaria(false)
	 * @return Response
	 * @throws InterruptedException, EntityNotFoundException
	 */
	@GET
	@Path("/ricercaGlobale")
	public Response ricercaSaccaGlobale(@QueryParam("nome") String nome,
									   @QueryParam("gruppo") String gruppoSanguigno,
									   @QueryParam("numero") String numeroSacche,
									   @QueryParam("dataArrivoMassima") String dataArrivoMassima,
									   @QueryParam("enteRichiedente") String enteRichiedente,
									   @QueryParam("indirizzoEnte") String indirizzoEnte,
									   @QueryParam("priorità") String priorita) throws InterruptedException, EntityNotFoundException {

		CcsRestApplication.logger.info("Ho ricevuto la richiesta per ricercare sacche di gruppo: "+gruppoSanguigno+" dal CTT: "+nome+" numeroSacche: "+numeroSacche+ " con priorità: "+priorita);
		Map<CTTName,String> cttOnline = ConnectionVerifier.isCTTOnline();
		//non devo ricercare le sacche presso il CTT richiedente
		cttOnline.remove(CTTName.getCttName(nome));
		CTT cttRichiedente = mm.getCTT(CTTName.getCttName(nome));
		CcsRestApplication.logger.info("Ecco i CTT che sono disponibili per la ricerca globale "+ cttOnline);
		//sacche che ho trovato nei vari ctt
		Map<CTTName,List<Sacca>> cttSacche = SearcherFactory.searcherMap.get(priorita).search(cttOnline, cttRichiedente,
				dataArrivoMassima, gruppoSanguigno, Integer.valueOf(numeroSacche));

		//devo controllare la risposta da dare al client
		List<Seriale>serialeList = new ArrayList<>();
		for (CTTName cttName : cttSacche.keySet())
			for(Sacca s: cttSacche.get(cttName)) {
				serialeList.add(s.getSeriale());
				if(MongoDataManager.getInstance().containsSacca(s.getSeriale())){
					MongoDataManager.getInstance().removeSacca(s.getSeriale());
					this.notifyCTT(NotificaSaccaInScadenzaMaker.creaNotificheSaccheInScadenza());
				}
			}
		
		CcsRestApplication.logger.info("Sacche trovate globalmente: "+ serialeList );

		//Richiesta soddisfatta completamente
		if(serialeList.size() == Integer.parseInt(numeroSacche)){
			this.prenotaSacca(cttOnline,cttSacche,indirizzoEnte,enteRichiedente);
			CCSRestClient.sendRisultatiRicerca(Settings.ip.get(cttRichiedente),
					new NotificaRisultatiRicerca(serialeList, "Esito ricerca globale: completata totalmente"));
			CcsRestApplication.logger.info("La richiesta del "+nome+" è stata soddisfatta");
			return Response.status(Response.Status.NO_CONTENT).build();
		}

		//Richiesta soddisfatta parzialmente
		if (serialeList.size() < Integer.parseInt(numeroSacche) && serialeList.size() != 0){
			this.prenotaSacca(cttOnline,cttSacche,indirizzoEnte,enteRichiedente);
			CCSRestClient.sendRisultatiRicerca(Settings.ip.get(mm.getCTT(CTTName.getCttName(nome))),
					new NotificaRisultatiRicerca(serialeList, "Esito ricerca globale: completata parzialmente.\n" +
							"Mancano: "+ (Integer.parseInt(numeroSacche)-serialeList.size())+" sacche."));
			CcsRestApplication.logger.info("Non è stato possibile soddisfare la richiesta del "+nome);
			return Response.status(Response.Status.NO_CONTENT).build();
		}

		//Nessuna sacca trovata
		this.prenotaSacca(cttOnline,cttSacche,indirizzoEnte,enteRichiedente);
		CCSRestClient.sendRisultatiRicerca(Settings.ip.get(mm.getCTT(CTTName.getCttName(nome))),
				new NotificaRisultatiRicerca(serialeList, "Nessuna sacca trovata presso i CTT della rete CCS"));
		CcsRestApplication.logger.info("Non sono riuscito a trovare nessuna sacca per il "+nome);
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	/**Metodo utilizzato per prenotare la sacca a seguito di una ricerca globale
	 * @param cttOnline Lista dei CTTOnline
	 * @param cttSacche Lista delle sacche trovate
	 * @param indirizzoEnte Indirizzo dell'ente richiedente
	 * @param enteRichiedente Ente richiedente
	 * @throws InterruptedException, EntityNotFoundException
	 */
	private void prenotaSacca(Map<CTTName, String> cttOnline, Map<CTTName, List<Sacca>> cttSacche, String indirizzoEnte, String enteRichiedente) throws InterruptedException, EntityNotFoundException {
		CcsRestApplication.logger.info("Inizio la procedura per prenotare le sacche");
		for (CTTName cttName : cttSacche.keySet()) {
			List<Seriale> serialiDaEvadere = new ArrayList<>();
			for (Sacca s : cttSacche.get(cttName)) {
				serialiDaEvadere.add(s.getSeriale());
				if(MongoDataManager.getInstance().containsSacca(s.getSeriale()))
					MongoDataManager.getInstance().removeSacca(s.getSeriale());
				this.notifyCTT(NotificaSaccaInScadenzaMaker.creaNotificheSaccheInScadenza());
			}

			CcsRestApplication.logger.info("Sto prenotando le sacche presso il : "+cttName.getCttname());
			CCSRestClient.makeEvasioneRequest(cttOnline.get(cttName),serialiDaEvadere, indirizzoEnte, enteRichiedente);
		}

	}

	@Override
	public void notifyCTT( List<Notifica> saccheInScadenza) {
		this.scadenzeObserver.update(saccheInScadenza);
	}
}