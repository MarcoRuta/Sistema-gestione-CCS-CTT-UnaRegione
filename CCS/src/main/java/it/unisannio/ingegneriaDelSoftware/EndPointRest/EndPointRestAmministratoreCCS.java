package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import com.itextpdf.text.DocumentException;
import it.unisannio.ingegneriaDelSoftware.Annotazioni.Secured;
import it.unisannio.ingegneriaDelSoftware.CcsRestApplication;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.*;
import it.unisannio.ingegneriaDelSoftware.ClientRest.CCSRestClient;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Functional.ConnectionVerifier;
import it.unisannio.ingegneriaDelSoftware.Functional.IDGenerator;
import it.unisannio.ingegneriaDelSoftware.Functional.PDFGenerator;
import it.unisannio.ingegneriaDelSoftware.Interfaces.EndPointAmministratoreCCS;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;
import it.unisannio.ingegneriaDelSoftware.Util.Settings;
import java.time.LocalDate;
import java.time.format.*;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

@Path("/CCS")
@Singleton
@Secured
@RolesAllowed("AmministratoreCCS")
public class EndPointRestAmministratoreCCS implements EndPointAmministratoreCCS{
	private MongoDataManager mm = MongoDataManager.getInstance();
	
	/**Aggiunge un nuovo CTT al Database dei CTT
	 * @param provincia La provincia del CTT che si vuole inserire
	 * @param citta La città del CTT che si vuole inserire
	 * @param indirizzo L'indirizzo del CTT che si vuole aggiungere
	 * @param telefono Il telefono del CTT che si vuole aggiungere
	 * @param email L'email del CTT che si vuole aggiungere
	 * @param latitudine La latitudine del CTT che si vuole aggiungere
	 * @param longitudine La longitudine del CTT che si vuole aggiungere
	 * @param uriInfo Informazioni riguardo l'uri
	 * @return Response */
	@POST
	@Path("/aggiuntaCTT")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addCTT(@FormParam("provincia") String provincia,
						   @FormParam("citta") String citta,
						   @FormParam("indirizzo") String indirizzo,
						   @FormParam("telefono") String telefono,
						   @FormParam("email") String email,
						   @FormParam("latitude") String latitudine,
						   @FormParam("longitude") String longitudine,
						   @Context UriInfo uriInfo) throws EntityAlreadyExistsException {
		CcsRestApplication.logger.info("Si è richiesta l'aggiunta di un CTT");
		CTT ctt = new CTT(new CTTName(),provincia, citta, telefono, indirizzo, email, Double.parseDouble(latitudine), Double.parseDouble(longitudine));
		mm.createCTT(ctt);
		CTTName.updateSettings();
		CcsRestApplication.logger.info("CTT aggiunto correttamente al DataBase");

		return Response
				.status(Response.Status.OK)
				.entity(ctt.getDenominazione().getCttname() + " aggiunto correttamente")
				.header(HttpHeaders.LOCATION, uriInfo.getBaseUri()+"/centers/"+ctt.getDenominazione().getCttname())
				.build();
	}

	/**Rimuove un CTT dal Database dei CTT
	 * @param cttName Il nome del CTT da rimuovere
	 * @return Response 
	 */
	@DELETE
	@Path("/rimozioneCTT/{cttName}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response removeCTT(@PathParam("cttName") String cttName) throws EntityNotFoundException, NumberFormatException {
		CcsRestApplication.logger.info("Si è richiesta la rimozione di un CTT");
		mm.removeCTT(CTTName.getCttName(cttName));
		CcsRestApplication.logger.info("Il CTT "+cttName+"è stato rimosso correttamente dal DB");
		return Response
				.status(Response.Status.OK)
				.entity(cttName + " rimosso correttamente")
				.build();
	}

	/**Restituisce la lista di tutti i CTT presenti nel Database dei CTT, utilizzato per l' aggiunta automatica dei CTT
	 * @return La lista dei CTT
	 */
	@GET
	@Path("/centers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CTT> listaCTT(){
		CcsRestApplication.logger.info("Si è richiesta la lista dei CTT presenti nel DataBase");
		return mm.getListaCTT();	
	}

	/**Aggiunge un AmministratoreCCS nel Database dei Dipendenti
	 * @param cdf Codice fiscale del Dipendente da aggiungere al DataBase
	 * @param nome Nome del Dipendente da aggiungere al DataBase
	 * @param cognome Cognome del Dipendente da aggiungere al DataBase
	 * @param dataDiNascita Data di nascita del Dipendente da aggiungere al DataBase
	 * @param username Username del Dipendente da aggiungere al DataBase
	 * @return Response 
	 */
	@POST
	@Path("/aggiuntaAmministratore")
	@Produces("application/pdf")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addAmministratore(@FormParam("cdf")String cdf,
								  @FormParam("nome")String nome,
								  @FormParam("cognome")String cognome,
								  @FormParam("dataDiNascita")String dataDiNascita,
								  @FormParam("username")String username) throws DateTimeParseException, IllegalArgumentException, AssertionError, EntityAlreadyExistsException {

		CcsRestApplication.logger.info("Si è richiesta l'aggiunta di un' Amministratore");
		String password = IDGenerator.getID();
		Dipendente d = new Dipendente(Cdf.getCDF(cdf), nome, cognome,
				LocalDate.parse(dataDiNascita, DateTimeFormatter.ofPattern(Constants.DATEFORMAT)),
				RuoloDipendente.AmministratoreCCS, username, password);
		mm.createDipendente(d);
		CcsRestApplication.logger.info("L'amministratore "+d.getCdf().getCodiceFiscale()+" è stato aggiunto correttamente al DataBase");

		return Response
				.status(Response.Status.CREATED)
				.entity(getPDF(cdf, username, password))
				.build();
	}

	/**Metodo tramite il quale è possibile recuperare un pdf con cdf, username e password di un dipendente
	 * @param cdf il cdf del Dipendente di cui si vogliono recuperare i dati
	 * @param username l'username del Dipendente
	 * @param password la password del Dipendente
	 * @return StreamingOutput StreamingOutput da dove verrà aperto il pdf generato
	 */
	private StreamingOutput getPDF(String cdf, String username, String password) {
		return new StreamingOutput() {
			public void write(OutputStream output) {
				try {
					PDFGenerator.makeDocumentDipendente(output, cdf, username, password);
				}catch (DocumentException | IOException e) {
					throw new WebApplicationException(Response
							.status(Response.Status.INTERNAL_SERVER_ERROR)
							.entity("Impossibile creare l'amministratore")
							.build());
				}
			}
		};
	}


	/**Rimuove un Dipendente dal DataBase
	 * @param header Il token di autentificazione
	 * @param cdf Codice fiscale del Dipendente da rimuovere dal DataBase
	 * @return Response
	 * @throws EntityNotFoundException, WebApplicationException
	 */
	@DELETE
	@Path("/rimozioneAmministratore/{cdf}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response removeAmministratore(@HeaderParam(HttpHeaders.AUTHORIZATION)String header,
									 @PathParam("cdf") String cdf) throws EntityNotFoundException {
		Dipendente deleter = Token.getDipendenteByToken(header.substring("Basic ".length()));
		if (deleter.getCdf().getCodiceFiscale().equals(cdf))
			throw new WebApplicationException(
					Response
							.status(Response.Status.FORBIDDEN)
							.entity("Non puoi cancellare te stesso")
							.build());

		mm.removeDipendente(Cdf.getCDF(cdf));
		return Response
				.status(Response.Status.OK)
				.entity("Corretta rimozione del Dipendente: " + cdf)
				.build();
	}
	
    /**Restituisce la lista di tutti i Dipendenti presenti nel database dei Dipendenti
	 * @param header Il token di autentificazione
	 * @return dipendenti Lista di tutti i Dipendenti
	 * @throws EntityNotFoundException
	 */
	@GET
	@Path("/amministratori")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Dipendente> getAmministratori(@HeaderParam(HttpHeaders.AUTHORIZATION) String header) throws EntityNotFoundException {
		CcsRestApplication.logger.info("Si è richiesta la lista degli amministratori presenti nel DB");
		List<Dipendente> dipendenti = mm.getListaDipendenti();
		dipendenti.remove(Token.getDipendenteByToken(header.substring("Basic ".length())));
		return dipendenti;
	}

	//--------------------REPORT DIPENDENTI RETE CTT--------------------
	/**Restituisce la lista dei Dipendenti di tutti i CTT presenti sulla rete del ruolo selezionato
	 *  @param ruolo Ruolo dei Dipendenti da cercare
	 * @return Response
	 */
	@GET
	@Path("/reportDipendentiCCS")
	@Produces(MediaType.APPLICATION_JSON)
	public Response reportDipendentiCCS(@QueryParam("ruolo")String ruolo) {

		CcsRestApplication.logger.info("Sto inizializzando il report degli operatori presenti nella rete regionale");
		Map<CTTName, String> cttOnline = ConnectionVerifier.isCTTOnline();
		Map<CTTName, List<Dipendente>> risultatoQuery = new HashMap<>();

		cttOnline.keySet()
				.stream()
				.forEach(cttName -> risultatoQuery.put(
						cttName,
						CCSRestClient.makeReportDipendenti(cttOnline.get(cttName),ruolo)));

		return Response
				.status(Response.Status.OK)
				.entity(risultatoQuery)
				.build();
	}

	//--------------------REPORT STATISTICO DEL NUMERO DI SACCHE PRESENTI A LIVELLO REGIONALE PER CIASCUN GRUPPO SANGUIGNO--------------------
	 /**Restituisce il numero di sacche presenti di ogni tipo nella regione
	  * @param headers Il token di autentificazione
	  * @return Response
	 */
	 @GET
	 @Path("/reportStatisticoSaccheCCS")
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response reportStatisticoSaccheRegionale(@HeaderParam(HttpHeaders.AUTHORIZATION) String headers){

		 CcsRestApplication.logger.info("Sto inizializzando il report di disponibilità regionale sacche");

		 Map<CTTName, String> cttOnline = ConnectionVerifier.isCTTOnline();
		 Map<GruppoSanguigno, Integer> risultatoQuery = new HashMap<GruppoSanguigno, Integer>();


		 //inizializzo a zero i risultati
		 Arrays.stream(GruppoSanguigno.values()).forEach( gs -> risultatoQuery.put(gs,0));

		 for(CTTName ctt : cttOnline.keySet()){
			 CcsRestApplication.logger.info("Interrogo " + ctt);
			 Map<GruppoSanguigno, Integer> temp = CCSRestClient.makeReportDisponibilitàSacche(cttOnline.get(ctt));
			 Arrays.stream(GruppoSanguigno.values())
					 .forEach(  gs -> risultatoQuery.put(gs, temp.get(gs.toString())+risultatoQuery.get(gs) ));
		 }

		 return Response
				 .status(Response.Status.OK)
				 .entity(risultatoQuery)
				 .build();

	 }

	//--------------------REPORT SACCHE INVIATE RETE REGIONALE--------------------
	/**Restituisce la lista dei DatiSacche relativi alle sacche che sono state affidate in un determinato arco temporale
	 * @param dataInizio Data inizio dell' arco temporale
	 * @param dataFine Data fine dell' arco temporale
	 * @return Response
	 * @throws DataFormatException
	 */
	@GET
	@Path("/reportSaccheInviateCCS")
	@Produces(MediaType.APPLICATION_JSON)
	public Response reportSaccheInviate(@QueryParam("dataInizio")String dataInizio,
										 @QueryParam("dataFine")String dataFine) throws DataFormatException {

		CcsRestApplication.logger.info("Sto inizializzando il report delle sacche inviate dalla rete tra il "+dataInizio+"e il "+dataFine);
		List<DatiSacca> risultatoQuery = new ArrayList<>();

		ConnectionVerifier.isCTTOnline()
				.values()
				.stream()
				.forEach( ip-> risultatoQuery.addAll(CCSRestClient.makeReportSaccheInviate(ip, dataInizio, dataFine)));


		return Response
				.status(Response.Status.OK)
				.entity(risultatoQuery)
				.build();
	}

	//--------------------REPORT SACCHE RICEVUTE RETE REGIONALE--------------------
	/**Restituisce la lista dei DatiSacche relativi alle sacche che sono state ricevute dalla rete in un determinato arco temporale
	 * @param dataInizio Data inizio dell' arco temporale
	 * @param dataFine Data fine dell' arco temporale
	 * @return Response
	 * @throws DataFormatException
	 */
	@GET
	@Path("/reportSaccheRicevuteCCS")
	@Produces(MediaType.APPLICATION_JSON)
	public Response reportSaccheRicevute(@QueryParam("dataInizio")String dataInizio,
										@QueryParam("dataFine")String dataFine) throws DataFormatException{

		CcsRestApplication.logger.info("Sto inizializzando il report delle sacche ricevute in rete tra il "+dataInizio+"e il "+dataFine);
		List<DatiSacca> risultatoQuery = new ArrayList<>();

		ConnectionVerifier.isCTTOnline()
				.values()
				.stream()
				.forEach( ip-> risultatoQuery.addAll(CCSRestClient.makeReportSaccheRicevute(ip, dataInizio, dataFine)));

		return Response
				.status(Response.Status.OK)
				.entity(risultatoQuery)
				.build();
	}

	//--------------------REPORT PERMANENZA MEDIA DELLE SACCHE DI SANGUE PER GRUPPO SANGUIGNO A LIVELLO REGIONALE--------------------
	/**Restituisce la giacenza media delle sacche di sangue all'interno dei magazzini dei CTT raggruppate per gruppo sanguigno
	 * @param headers Il token di autentificazione
	 * @return Response
	 */
	@GET
	@Path("/giacenzaMediaSaccheCCS")
	@Produces(MediaType.APPLICATION_JSON)
	public Response giacenzaMediaSaccheRegionale(@HeaderParam(HttpHeaders.AUTHORIZATION) String headers){

		CcsRestApplication.logger.info("Sto inizializzando il report di permanenza regionale sacche");
		Map<CTTName, String> cttOnline = ConnectionVerifier.isCTTOnline();
		Map<GruppoSanguigno, Double> risultatoQuery = new HashMap<>();

		//inizializzo a zero i risultati
		Arrays.stream(GruppoSanguigno.values()).forEach( gs -> risultatoQuery.put(gs,0.0));

		for(CTTName ctt : cttOnline.keySet()){
			Map<GruppoSanguigno, Double> temp = CCSRestClient.makeReportGiacenzaMediaSacche(cttOnline.get(ctt));
			Arrays.stream(GruppoSanguigno.values())
					.forEach(  gs -> risultatoQuery.put(gs, temp.get(gs.toString())+risultatoQuery.get(gs) ));
		}

		risultatoQuery.keySet()
				.stream()
				.forEach(gs -> risultatoQuery.replace(
						gs,
						(risultatoQuery.get(gs)/cttOnline.size())));


		return Response
				.status(Response.Status.OK)
				.entity(risultatoQuery)
				.build();

	}

	/**Restituisce una mappa di <String,Boolean>, true se il CTT è online, false altrimenti
	 * @param headers Il token di autentificazione
	 * @return Response
	 * @throws EntityNotFoundException
	 */
	@GET
	@Path("/statusReteCtt")
	@Produces(MediaType.APPLICATION_JSON)
	public Response statusReteCtt(@HeaderParam(HttpHeaders.AUTHORIZATION) String headers) throws EntityNotFoundException {

		Map<String,Boolean> statusRete = Settings.ip.keySet()
				.stream()
				.collect(Collectors.toMap(
						(CTT ctt) -> ctt.getDenominazione().getCttname()
								+" - "+ctt.getPosizione().getCitta()
								+" - "+ctt.getPosizione().getProvincia(),
						(CTT ctt) -> ConnectionVerifier.isCTTOnline().keySet().contains(ctt.getDenominazione())));

		return Response
				.status(Response.Status.OK)
				.entity(statusRete)
				.build();

	}
}