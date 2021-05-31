package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import it.unisannio.ingegneriaDelSoftware.Annotazioni.Secured;
import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaEvasione;
import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaSaccaInScadenza;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.NotificheObservers.TerminaleMagazziniereObserver;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Observer;
import it.unisannio.ingegneriaDelSoftware.Exceptions.SaccheInLocaleNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.EndPointOperatoreCTT;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Searcher;
import WebSocket.ClientEndPoint.SaccheInScadenzaClientEndPoint;
import it.unisannio.ingegneriaDelSoftware.Searcher.CompositionSearcher;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;


@Path("/operatore")
@Singleton
@Secured
@RolesAllowed("OperatoreCTT")
public class EndPointRestOperatoreCTT implements EndPointOperatoreCTT{

	private MongoDataManager md = MongoDataManager.getInstance();

	/**Il terminale del magazziniere deve essere notificato quando viene creata una notifica*/
	private Observer anObserver= new TerminaleMagazziniereObserver();
	/**Composite che effettua la ricerca prima in locale con il gruppo sanguigno specificato e poi in locale con i gs compatibili*/
	public Searcher aSearcher = new CompositionSearcher();

	/**Restituisce la Sacca del GruppoSanguigno richiesto con Data di scadenza più vicina nel DataBase locale.
	 * @param gruppoSanguigno Gruppo sanguigno ricercato
	 * @param dataArrivoMassima Data entro la quale la Sacca non deve scadere e deve arrivare all'Ente richiedente
	 * @param enteRichiedente Ente richiedente della Sacca
	 * @return la lista di sacche ricercate
	 *
	 */
	@GET
	@Path("/ricerca")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ricercaSaccaLocale(@QueryParam("gruppoSanguigno") String gruppoSanguigno,
									   @QueryParam("numeroSacche") String numeroSacche,
									   @QueryParam("dataArrivoMassima") String dataArrivoMassima,
									   @QueryParam("enteRichiedente") String enteRichiedente,
									   @QueryParam("indirizzoEnte") String indirizzoEnte,
									   @QueryParam("priorità") String priorita) throws InterruptedException {
		try {
			List<Sacca> saccheTrovate = new ArrayList<Sacca>();
			List<String> serialiDaEvadere = new ArrayList<>();
			int numSacche = Integer.parseInt(numeroSacche);
			saccheTrovate = this.aSearcher.search(GruppoSanguigno.valueOf(gruppoSanguigno),
					numSacche,
					LocalDate.parse(dataArrivoMassima, DateTimeFormatter.ofPattern(Constants.DATEFORMAT)));

			if (!saccheTrovate.isEmpty()) {
				for (Sacca sacca : saccheTrovate) {
					if(sacca.getDataScadenza().isBefore(LocalDate.now().plusDays(3))
							|| sacca.getDataScadenza().isEqual(LocalDate.now().plusDays(3)))
						this.notifyCCS(sacca);

					serialiDaEvadere.add(sacca.getSeriale().getSeriale());
				}
				CttDataBaseRestApplication.logger.info("Ho trovato delle sacche in locale");
			}

			if (saccheTrovate.size() < numSacche) throw new SaccheInLocaleNotFoundException("Verra contattato il CSS");

			return Response
					.status(Response.Status.OK) //In questo caso sono state trovate tutte le sacche, va inviata risposta 200 OK 
					.entity(new NotificaEvasione(serialiDaEvadere, enteRichiedente, indirizzoEnte))//Va gestito l'inoltro della notifica di evasione sacche presso l'interfaccia REST del magazziniere
					.build();
		} catch (SaccheInLocaleNotFoundException e) {
			return Response //in realtà deve avviare RicercaSaccaGlobale e passare i parametri al CCS
					.status(Response.Status.NOT_FOUND)
					.entity(e.getMessage())
					.build();
		}
	}


	private void notifyCCS(Sacca sacca) throws InterruptedException {
		try{
			Client client = ClientBuilder.newClient();
			WebTarget evasioneSacca = client
					.target(Constants.CCSIP+"/rest/CCS/ritiroAlertCTT/")
					.path(sacca.getSeriale().getSeriale());
			evasioneSacca.request().delete();
			CttDataBaseRestApplication.logger.info("Avviso il CCS che ho consumato una delle Sacche in Scadenza");
		}catch (Exception e){
			wait(1000*60*2);
			this.notifyCCS(sacca);

		}

	}

}