package it.unisannio.ingegneriaDelSoftware.EndPointRest.RicercaGlobale;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.mongodb.Mongo;
import it.unisannio.ingegneriaDelSoftware.CcsDataBaseRestApplication;
import it.unisannio.ingegneriaDelSoftware.Classes.Beans.Seriale;
import it.unisannio.ingegneriaDelSoftware.Classes.CTT;
import it.unisannio.ingegneriaDelSoftware.Classes.CTTName;
import it.unisannio.ingegneriaDelSoftware.Classes.Beans.Sacca;
import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaRisultatiRicerca;
import it.unisannio.ingegneriaDelSoftware.ClientRest.CCSRestClient;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.SaccheInScadenza.SaccheInScadenzaObserver;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Functional.ConnectionVerifier;
import it.unisannio.ingegneriaDelSoftware.Functional.NotificaSaccaInScadenzaMaker;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Observer;
import it.unisannio.ingegneriaDelSoftware.Searchers.SearcherFactory;
import it.unisannio.ingegneriaDelSoftware.Util.Settings;

@Path("/CCS")
@Singleton
public class EndPointRestRicercaGlobale {


	Observer scadenzeObserver = new SaccheInScadenzaObserver();
	
	/**Restituisce la Sacca del GruppoSanguigno richiesto con Data di scadenza più vicina nel DataBase locale.
	 * @param gruppoSanguigno Gruppo sanguigno ricercato
	 * @param numeroSacche il numero di sacche richieste
	 * @param dataArrivoMassima Data entro la quale la Sacca non deve scadere e deve arrivare all'Ente richiedente
	 * @param enteRichiedente Ente richiedente della Sacca
	 * @return Response, 200 ok se le sacche sono state trovate e evase in locale, 404 NOT_FOUND non è stato possibile soddisfare la ricerca in locale
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


		CcsDataBaseRestApplication.logger.info("Ho ricevuto la richiesta per ricercare sacche di gruppo: "+gruppoSanguigno+" dal CTT: "+nome+" numeroSacche: "+numeroSacche+ " con priorità: "+priorita);
		//ctt che sono online
		Map<CTTName,String> cttOnline = ConnectionVerifier.isCTTOnline();
		//non devo ricercare le sacche presso il CTT richiedente
		cttOnline.remove(CTTName.getCttName(nome));

		CTT cttRichiedente = MongoDataManager.getInstance().getCTT(CTTName.getCttName(nome));
		CcsDataBaseRestApplication.logger.info("Ecco i CTT che sono disponibili per la ricerca globale "+ cttOnline);

		//sacche che ho trovato nei vari ctt, sono gia quelle necessarie
		Map<CTTName,List<Sacca>> cttSacche = SearcherFactory.searcherMap.get(priorita).search(cttOnline, cttRichiedente,
				dataArrivoMassima, gruppoSanguigno, Integer.valueOf(numeroSacche));


		//devo controllare la risposta da dare al client
		List<Seriale>serialeList = new ArrayList<>();
		for (CTTName cttName : cttSacche.keySet())
			for(Sacca s: cttSacche.get(cttName)) {
				serialeList.add(s.getSeriale());
				if(MongoDataManager.getInstance().containsSacca(s.getSeriale())){
					MongoDataManager.getInstance().removeSacca(s.getSeriale());
					scadenzeObserver.update(NotificaSaccaInScadenzaMaker.creaNotificheSaccheInScadenza());
				}
			}
		
		CcsDataBaseRestApplication.logger.info("Sacche trovate globalmente: "+ serialeList );


		if(serialeList.size() == Integer.parseInt(numeroSacche)){
			this.prenotaSacca(cttOnline,cttSacche,indirizzoEnte,enteRichiedente);
			CCSRestClient.sendRisultatiRicerca(Settings.ip.get(cttRichiedente.getDenominazione()),
					new NotificaRisultatiRicerca(serialeList, "Esito ricerca globale: completata totalmente"));
			CcsDataBaseRestApplication.logger.info("La richiesta del "+nome+"è stata soddisfatta");
			return Response.status(Response.Status.NO_CONTENT).build();
		}


		if (serialeList.size() < Integer.parseInt(numeroSacche) && serialeList.size() != 0){
			this.prenotaSacca(cttOnline,cttSacche,indirizzoEnte,enteRichiedente);
			CCSRestClient.sendRisultatiRicerca(Settings.ip.get(CTTName.getCttName(nome)),
					new NotificaRisultatiRicerca(serialeList, "Esito ricerca globale: completata parzialmente.\n" +
							"Mancano: "+ (Integer.parseInt(numeroSacche)-serialeList.size())+" sacche."));
			CcsDataBaseRestApplication.logger.info("Non è stato possibile soddisfare la richiesta del "+nome);
			return Response.status(Response.Status.NO_CONTENT).build();
		}



		//non ho trovato nulla
		this.prenotaSacca(cttOnline,cttSacche,indirizzoEnte,enteRichiedente);
		CCSRestClient.sendRisultatiRicerca(Settings.ip.get(CTTName.getCttName(nome)),
				new NotificaRisultatiRicerca(serialeList, "Nessuna sacca trovata presso i CTT della rete CCS"));
		CcsDataBaseRestApplication.logger.info("Non sono riuscito a trovare nessuna sacca per il "+nome);
		return Response.status(Response.Status.NOT_FOUND).build();


	}

	private void prenotaSacca(Map<CTTName, String> cttOnline, Map<CTTName, List<Sacca>> cttSacche, String indirizzoEnte, String enteRichiedente) throws InterruptedException, EntityNotFoundException {
		CcsDataBaseRestApplication.logger.info("Inizio la procedura per prenotare le sacche");
		for (CTTName cttName : cttSacche.keySet()) {
			List<Seriale> serialiDaEvadere = new ArrayList<>();
			for (Sacca s : cttSacche.get(cttName)) {
				serialiDaEvadere.add(s.getSeriale());
				if(MongoDataManager.getInstance().containsSacca(s.getSeriale()))
					MongoDataManager.getInstance().removeSacca(s.getSeriale());
				this.scadenzeObserver.update(NotificaSaccaInScadenzaMaker.creaNotificheSaccheInScadenza());
			}

			CcsDataBaseRestApplication.logger.info("Sto prenotando le sacche presso il : "+cttName.getCttname());
			CCSRestClient.makeEvasioneRequest(cttOnline.get(cttName),serialiDaEvadere, indirizzoEnte, enteRichiedente);
		}

	}


}
