package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import it.unisannio.ingegneriaDelSoftware.Annotazioni.Secured;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Wrapper.SaccaWrapper;
import it.unisannio.ingegneriaDelSoftware.ClientRest.CTTRestClient;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Sacca;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Seriale;
import it.unisannio.ingegneriaDelSoftware.CttRestApplication;
import it.unisannio.ingegneriaDelSoftware.Functional.ConnectionVerifier;
import it.unisannio.ingegneriaDelSoftware.Interfaces.EndPointOperatoreCTT;
import it.unisannio.ingegneriaDelSoftware.Searcher.CompositionSearcher;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;
import it.unisannio.ingegneriaDelSoftware.ResponseHandler.ResponseBuilderFactory;
import it.unisannio.ingegneriaDelSoftware.Util.Settings;


@Path("/operatore")
@Singleton
@Secured
@RolesAllowed({"OperatoreCTT","CCS"})
public class EndPointRestOperatoreCTT implements EndPointOperatoreCTT{

	/**Composite che effettua la ricerca prima in locale con il gruppo sanguigno specificato e poi in locale con i gs compatibili*/
	public CompositionSearcher aSearcher = new CompositionSearcher();


	/**Restituisce la Sacca del GruppoSanguigno richiesto con Data di scadenza più vicina nel DataBase locale.
	 * @param gruppoSanguigno Gruppo sanguigno ricercato
	 * @param numeroSacche il numero di sacche richieste
	 * @param dataArrivoMassima Data entro la quale la Sacca non deve scadere e deve arrivare all'Ente richiedente
	 * @param enteRichiedente Ente richiedente della Sacca
	 * @return Response
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

		if (LocalDate.parse(dataArrivoMassima).isBefore(LocalDate.now()))
			throw new IllegalArgumentException("Data affidamento inserita non valida.");

		if (Integer.parseInt(numeroSacche) < 0)
			throw new IllegalArgumentException("Numero di sacche inserito non valido.");

		CttRestApplication.logger.info("Ho ricevuto la richiesta per ricercare "+ numeroSacche +" sacche di gruppo: "+gruppoSanguigno);
		
		List<Sacca> saccheTrovate = new ArrayList<Sacca>();
		List<Seriale> serialiDaEvadere = new ArrayList<>();
		int numSacche = Integer.parseInt(numeroSacche);
		
		saccheTrovate = this.aSearcher.search(GruppoSanguigno.valueOf(gruppoSanguigno),
				numSacche,
				LocalDate.parse(dataArrivoMassima, DateTimeFormatter.ofPattern(Constants.DATEFORMAT)));

		if (!saccheTrovate.isEmpty()) {
			for (Sacca sacca : saccheTrovate) {
				if((sacca.getDataScadenza().isBefore(LocalDate.now().plusDays(3))
						|| sacca.getDataScadenza().isEqual(LocalDate.now().plusDays(3)))
						&& ConnectionVerifier.isCCSOnline())
					//contatto il ccs dato che è online
					CTTRestClient.notifyEvasioneSaccaToCCS(sacca);

				serialiDaEvadere.add(sacca.getSeriale());
			}
		}

		CttRestApplication.logger.info("Ricerca conclusa, ecco le sacche che ho trovato: "+serialiDaEvadere);

		return ResponseBuilderFactory.GetResponseHandler(serialiDaEvadere,numSacche).makeResearchResponse(numSacche, serialiDaEvadere, enteRichiedente, indirizzoEnte, dataArrivoMassima, priorita, gruppoSanguigno);
	}


	/**Restituisce tutte le sacche del tipo indicato e compatibili che hanno data di scadenza successiva alla data dell'utilizzo
	 * @param gruppoSanguigno Gruppo sanguigno ricercato
	 * @param dataArrivoMassima Data entro la quale la Sacca non deve scadere e deve arrivare all'Ente richiedente
	 * @return Response
	 */
	@GET
	@Path("/listaSaccheCompatibili/{gs}/{data}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ricercaSaccheCCS(@PathParam("gs") String gruppoSanguigno,
									 @PathParam("data") String dataArrivoMassima){

		CttRestApplication.logger.info("Ho ricevuto la richiesta da parte del CCS per ricercare delle sacche di gruppo: "+gruppoSanguigno);

		List<Sacca> saccheTrovate =
				MongoDataManager.getInstance().getListaSacche()
				.stream()
				.filter(sacca -> !sacca.isPrenotato())
				.filter(sacca -> sacca.getDataScadenza().isAfter(LocalDate.parse(dataArrivoMassima, DateTimeFormatter.ofPattern(Constants.DATEFORMAT))))
				.filter(sacca -> sacca.getDataScadenza().isAfter(LocalDate.now()))
				.filter(sacca -> GruppoSanguigno.puoRicevereDa(GruppoSanguigno.valueOf(gruppoSanguigno)).contains(sacca.getGruppoSanguigno()))
				.sorted(Comparator.comparing((Sacca s)-> s.getDataScadenza()))
				.collect(Collectors.toList());



		return Response
				.status(Response.Status.OK)
				.entity(new SaccaWrapper(saccheTrovate))
				.build();
}

	
	/**Alert della prenotazione della Sacca
	 * @param seriale Il Seriale della Sacca
	 * @param indirizzoEnte L'indirizzo dell'ente che ha richiesto la Sacca
	 * @param enteRichiedente L'ente che ha richiesto la Sacca
	 * @return Response
	 */
	@POST
	@Path("/prenotaSaccaInScadenza/{seriale}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response prenotaSaccaAlert(@PathParam("seriale") String seriale,
									  @FormParam("indirizzoEnte") String indirizzoEnte,
									  @FormParam("enteRichiedente")String enteRichiedente){
		CttRestApplication.logger.info("L'operatore ha richiesto l'evasione per la sacca: "+seriale);
		Client client = ClientBuilder.newClient();
		WebTarget gestioneSaccheInscadenza = client.target("http://"+Settings.ccsIp+":"+Settings.ccsIpPort+"/rest/CCS/prenotaSaccaInScadenza")
				.path(seriale).queryParam("enteRichiedente",enteRichiedente).queryParam("indirizzoEnte",indirizzoEnte);
		CttRestApplication.logger.info("Richiesta inoltrata verso: "+gestioneSaccheInscadenza.getUri());
		return gestioneSaccheInscadenza.request().delete(Response.class);
	}

}