package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import it.unisannio.ingegneriaDelSoftware.Annotazioni.Secured;
import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.NotificaEvasione;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import it.unisannio.ingegneriaDelSoftware.NotificheObservers.TerminaleMagazziniereObserver;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Observer;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Subject;
import it.unisannio.ingegneriaDelSoftware.Exceptions.SaccheInLocaleNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.EndPointOperatoreCTT;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Notifica;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Searcher;
import it.unisannio.ingegneriaDelSoftware.Searcher.CompositionSearcher;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;


@Path("/operatore")
@Singleton
@Secured
@RolesAllowed("OperatoreCTT")
public class EndPointRestOperatoreCTT implements EndPointOperatoreCTT, Subject{

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
									   @QueryParam("priorità") String priorita) {
		try {
			List<Sacca> saccheTrovate = new ArrayList<Sacca>();
			List<String> serialiDaEvadere = new ArrayList<>();
			int numSacche = Integer.parseInt(numeroSacche);
			saccheTrovate = this.aSearcher.search(GruppoSanguigno.valueOf(gruppoSanguigno),
					numSacche,
					LocalDate.parse(dataArrivoMassima, DateTimeFormatter.ofPattern(Constants.DATEFORMAT)));
			if (!saccheTrovate.isEmpty()) {
				for (Sacca sacca : saccheTrovate) {
					md.setPrenotatoSacca(sacca.getSeriale());
					serialiDaEvadere.add(sacca.getSeriale().getSeriale());
				}
				this.notifyMagazziniereObserver(new NotificaEvasione(serialiDaEvadere, enteRichiedente, indirizzoEnte));
			}

			if (saccheTrovate.size() < numSacche) throw new SaccheInLocaleNotFoundException("Verra contattato il CSS");

			return Response
					.status(Response.Status.OK) //In questo caso sono state trovate tutte le sacche, va inviata risposta 200 OK 
					.entity(saccheTrovate)//Va gestito l'inoltro della notifica di evasione sacche presso l'interfaccia REST del magazziniere 
					.build();
		} catch (SaccheInLocaleNotFoundException e) {
			return Response //in realtà deve avviare RicercaSaccaGlobale e passare i parametri al CCS
					.status(Response.Status.NOT_FOUND)
					.entity(e.getMessage())
					.build();
		} catch (EntityNotFoundException e) {
			return 	Response //in realtà deve avviare RicercaSaccaGlobale e passare i parametri al CCS
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getMessage())
					.build();
		}
	}

	/**Il terminale del magazziniere è un subject, esso crea notifiche, le notifiche sono di interesse per
	 * il terminale magazziniere che deve essere notificato*/
	@Override
	public void notifyMagazziniereObserver(Notifica notifica) {
		this.anObserver.update(notifica);
	}

	@Override
	public void notifyOperatoreObserver(Notifica notifica) {
		//do nothing
	}
}