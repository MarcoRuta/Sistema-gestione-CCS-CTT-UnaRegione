package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;
import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.itextpdf.text.DocumentException;
import it.unisannio.ingegneriaDelSoftware.Annotazioni.Secured;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.*;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Functional.IDGenerator;
import it.unisannio.ingegneriaDelSoftware.Interfaces.EndPointAmministratoreCTT;
import it.unisannio.ingegneriaDelSoftware.PDF.PDFGenerator;
import it.unisannio.ingegneriaDelSoftware.Util.*;

@Path("/amministratore")
@Singleton
@Secured
@RolesAllowed({"AmministratoreCTT", "CCS"})

public class EndPointRestAmministratoreCTT implements EndPointAmministratoreCTT {
	private MongoDataManager md = MongoDataManager.getInstance();


	/**Metodo tramite il quale è possibile recuperare un pdf con cdf, username e password di un dipendente
	 * @param cdf il cdf del Dipendente di cui si vogliono recuperare i dati
	 * @return StreamingOutput StreamingOutput da dove verrà aperto il pdf generato
	 */
	@GET
	@Path("aggiuntaDipendente/pdf/{cdf}")
	@Produces("application/pdf")
	@Consumes(MediaType.TEXT_PLAIN)
	public StreamingOutput getPDF(@PathParam("cdf")String cdf){
		return new StreamingOutput() {
			public void write(OutputStream output){
				try {
					Dipendente dip = md.getDipendente(Cdf.getCDF(cdf));
					PDFGenerator.makeDocumentDipendente(output, cdf,dip.getUsername(),dip.getPassword());
				} catch (DocumentException | IOException e) {
					throw new WebApplicationException(Response
							.status(Response.Status.INTERNAL_SERVER_ERROR)
							.entity("Impossibile creare il dipendente")
							.build());
				} catch (EntityNotFoundException e) {
					throw new WebApplicationException(Response
							.status(Response.Status.NOT_FOUND)
							.entity("Impossibile creare il dipendente")
							.build());
				}
			}
		};
	}
	
	
	/**Aggiunge un Dipendente al DataBase
	 * @param cdf Dipendente da aggiungere al DataBase
	 * @param nome Il nome del Dipendente
	 * @param cognome Il cognome del Dipendente
	 * @param dataDiNascita La data di nascita del Dipendente
	 * @param ruolo Il ruolo del Dipendente
	 * @param username L'useername del Dipendente
	 * @return Response
	 * @throws DateTimeParseException, IllegalArgumentException, AssertionError, EntityAlreadyExistsException
	 */
	@POST
	@Path("/aggiuntaDipendente")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addDipendente(@FormParam("cdf")String cdf,
								  @FormParam("nome")String nome,
								  @FormParam("cognome")String cognome,
								  @FormParam("dataDiNascita")String dataDiNascita,
								  @FormParam("ruolo")String ruolo,
								  @FormParam("username")String username,
								  @Context UriInfo uriInfo) throws DateTimeParseException, IllegalArgumentException, AssertionError, EntityAlreadyExistsException {

		//creo un dipendente
		String password = IDGenerator.getID();
		Dipendente d = new Dipendente(Cdf.getCDF(cdf), nome, cognome,
				LocalDate.parse(dataDiNascita, DateTimeFormatter.ofPattern(Constants.DATEFORMAT)),
				RuoloDipendente.valueOf(ruolo), username, password);
		//aggiungo il dipendente al DB
		md.createDipendente(d);
		return Response
				.status(Response.Status.CREATED)
				.entity("Dipendente aggiunto correttamente")
				.header(HttpHeaders.CONTENT_LOCATION,uriInfo.getAbsolutePath().getPath()+"/pdf/"+d.getCdf().getCodiceFiscale())
				.build();
	}


	/**Rimuove un Dipendente dal DataBase
	 * @param cdf Codice fiscale del Dipendente da rimuovere dal DataBase
	 * @return Response 
	 * @throws EntityNotFoundException se si vuole rimuovere un Dipendente non presente nel DB
	 */
	@DELETE
	@Path("/rimozioneDipendente/{cdf}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response removeDipendente(@HeaderParam(HttpHeaders.AUTHORIZATION)String header,
									 @PathParam("cdf") String cdf) throws EntityNotFoundException {
		Dipendente deleter = Token.getDipendenteByToken(header.substring("Basic ".length()));
		if (deleter.getCdf().getCodiceFiscale().equals(cdf))
			throw  new WebApplicationException(
					Response
					.status(Response.Status.FORBIDDEN)
					.entity("Non puoi cancellare te stesso")
					.build());

		md.removeDipendente(Cdf.getCDF(cdf));
		return Response
				.status(Response.Status.OK)
				.entity("Corretta rimozione del Dipendente: " + cdf)
				.build();
	}


	/*---------REPORT OPERATORI CTT------------
	 /**Restituisce la lista dei Dipendenti del CTT che occupano il Ruolo scelto
	 * @param ruolo Ruolo dei Dipendenti da cercare
	 * @return Response
	 */
	@GET
	@Path("/reportDipendentiCtt")
	@Produces(MediaType.APPLICATION_JSON)
	public Response reportDipendentiCTT(@QueryParam("ruolo")String ruolo){

		List<Dipendente> listaDipendenti = md.getListaDipendenti();
		List<Dipendente> risultatoQuery = new ArrayList<Dipendente>();

		for(Dipendente d : listaDipendenti)
			if(d.getRuolo().toString().equals(ruolo)) risultatoQuery.add(d);

		return Response
				.status(Response.Status.OK)
				.entity(risultatoQuery)
				.build();
	}


	/*---------REPORT NUMERICO DEI TIPI DI SACCHE PRESENTI NEL DATABASE------------
	 /**Restituisce il numero di Sacche presenti di ogni gruppo sanguigno nel database delle Sacche
	 * @return Response 
	 */
	@GET
	@Path("/reportStatisticoSacche")
	@Produces(MediaType.APPLICATION_JSON)
	public Response reportStatisticoSacche(@HeaderParam(HttpHeaders.AUTHORIZATION) String headers){

		Map<GruppoSanguigno,Integer> risultatoQuery = this.getNumeroSaccheDB();

		return Response
				.status(Response.Status.OK)
				.entity(risultatoQuery)
				.build();
	}

	
	/*---------REPORT SACCHE INVIATE IN UN PERIODO------------
	 /**Restituisce la lista dei DatiSacche relativi alle sacche che sono state affidate in un determinato arco temporale
	 * @param dataInizio Data inizio dell' arco temporale
	 * @param dataFine Data fine dell' arco temporale
	 * @return Response
	 */
	@GET
	@Path("/reportLocaleSaccheInviate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response reportLocaleSaccheInviate(@QueryParam("dataInizio")String dataInizio,
											  @QueryParam("dataFine")String dataFine) throws DateTimeParseException{
		LocalDate dataInizioReport = LocalDate.parse(dataInizio, DateTimeFormatter.ofPattern(Constants.DATEFORMAT));
		LocalDate dataAffidamentoReport = LocalDate.parse(dataFine, DateTimeFormatter.ofPattern(Constants.DATEFORMAT));
		List <DatiSacca> risultatoQuery = getSaccheInviatePeriodoTemporale(dataInizioReport,dataAffidamentoReport);
		return Response
				.status(Response.Status.OK)
				.entity(risultatoQuery)
				.build();
	}

	
	/*---------REPORT SACCHE RICEVUTE IN UN PERIODO------------
	 /**Restituisce la lista dei DatiSacche relativi alle sacche che sono state ricevute in un determinato arco temporale
	 * @param dataInizio Data inizio dell' arco temporale
	 * @param dataFine Data fine dell' arco temporale
	 * @return Response
	 */
	@GET
	@Path("/reportLocaleSaccheRicevute")
	@Produces(MediaType.APPLICATION_JSON)
	public Response reportLocaleSaccheRicevute(@QueryParam("dataInizio")String dataInizio,
											   @QueryParam("dataFine")String dataFine) throws DateTimeParseException{
		LocalDate dataInizioReport = LocalDate.parse(dataInizio, DateTimeFormatter.ofPattern(Constants.DATEFORMAT));
		LocalDate dataAffidamentoReport = LocalDate.parse(dataFine, DateTimeFormatter.ofPattern(Constants.DATEFORMAT));
		List <DatiSacca> risultatoQuery = getSaccheRicevutePeriodoTemporale(dataInizioReport,dataAffidamentoReport);
		return Response
				.status(Response.Status.OK)
				.entity(risultatoQuery)
				.build();
	}

	
	/*---------REPORT PERMANENZA MEDIA PER TIPO DI SANGUE------------
	 /**Calcola quanto è il tempo medio di giacenza delle Sacche di sangue all'interno del magazzino
	 * @return Response
	 */
	@GET
	@Path("/giacenzaMediaSacche")
	@Produces(MediaType.APPLICATION_JSON)
	public Response giacenzaMedia(){

		Map<GruppoSanguigno,Double> risultatoQuery = this.giacenzaMediaMagazzino();
		return Response
				.status(Response.Status.OK)
				.entity(risultatoQuery)
				.build();
	}


	/**Restituisce la lista di Dipendenti contenuti nel database dei Dipendenti 
	 * @return la lista di Dipendenti che lavorano al CTT
	 * @throws EntityNotFoundException*/
	@GET
	@Path("/dipendenti")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Dipendente> getDipendenti(@HeaderParam(HttpHeaders.AUTHORIZATION) String header) throws EntityNotFoundException {
		List<Dipendente> dipendenti = md.getListaDipendenti();
		return dipendenti;
	}

	
	/**Restituisce una mappa con il numero di Sacche contenute nel database per ogni gruppo sanguigno
	 * @return mappa con key: gruppoSanguigno value: numero di sacche
	 */
	private Map<GruppoSanguigno,Integer> getNumeroSaccheDB(){

		List<Sacca> listaSacche = md.getListaSacche();
		int x;
		Map<GruppoSanguigno,Integer> risultatoQuery = new HashMap<GruppoSanguigno,Integer>();

		for(GruppoSanguigno g : GruppoSanguigno.values())
			risultatoQuery.put(g,0);

		for(Sacca s : listaSacche) {
			x = risultatoQuery.get(s.getGruppoSanguigno());
			x++;
			risultatoQuery.put(s.getGruppoSanguigno(), x);
		}
		return risultatoQuery;
	}

	
	/**Restituisce una lista di DatiSacca che sono state inviate tra dataInizioReport e dataFineReport
	 * @param dataInizioReport data dalla quale si selezionano le Sacche
	 * @param dataFineReport data oltre la quale non si selezionano più le Sacche
	 * @return lista di Sacche che sono state inviate dal CTT nel periodo selezionato*/
	private List<DatiSacca> getSaccheInviatePeriodoTemporale(LocalDate dataInizioReport, LocalDate dataFineReport){
		List<DatiSacca> datiSaccaTransitati = new ArrayList<>();

		//creo la lista dei dati sacca
		List<DatiSacca> listaDatiSacca = md.getListaDatiSacche();
		for (DatiSacca datiSacca : listaDatiSacca)
			if (datiSacca.getDataAffidamento().isPresent() &&
					(datiSacca.getDataAffidamento().get().isAfter(dataInizioReport) || datiSacca.getDataAffidamento().get().isEqual(dataInizioReport))
					&&
					(datiSacca.getDataAffidamento().get().isBefore(dataFineReport) || datiSacca.getDataAffidamento().get().isEqual(dataFineReport)))

				//se la sacca è stata inviata in un periodo compreso tra dataInizioReport e dataFineReport viene aggiunta alla lista
				datiSaccaTransitati.add(datiSacca);

		return datiSaccaTransitati;
	}

	
	/**Restituisce una lista di DatiSacca che sono state ricevute tra dataInizioReport e dataFineReport
	 * @param dataInizioReport data dalla quale si selezionano le Sacche
	 * @param dataFineReport data oltre la quale non si selezionano più le Sacche
	 * @return lista di Sacche che sono state ricevute dal CTT nel periodo selezionato*/
	private List<DatiSacca> getSaccheRicevutePeriodoTemporale(LocalDate dataInizioReport, LocalDate dataFineReport){
		List<DatiSacca> datiSaccaTransitati = new ArrayList<>();

		//creo la lista dei dati sacca
		List<DatiSacca> listaDatiSacca = md.getListaDatiSacche();
		for (DatiSacca datiSacca : listaDatiSacca)
			if ((datiSacca.getDataArrivo().isAfter(dataInizioReport) || datiSacca.getDataArrivo().isEqual(dataInizioReport))
					&&
					(datiSacca.getDataArrivo().isBefore(dataFineReport) || datiSacca.getDataArrivo().isEqual(dataFineReport)))

				//se la sacca è stata inviata in un periodo compreso tra dataInizioReport e dataFineReport viene aggiunta alla lista
				datiSaccaTransitati.add(datiSacca);

		return datiSaccaTransitati;
	}

	
	/**Restituisce la permanenza media delle sacche in magazzino per ogni tipo
	 * @return mappa con key: gruppoSanguigno e value: permanenza media per quel gruppo sanguigno
	 */
	private Map<GruppoSanguigno,Double> giacenzaMediaMagazzino(){
		Double days;
		int x;
		Double y;

		//Mappa in cui salvo il numero di sacche trovate per tipo
		Map<GruppoSanguigno,Integer> saccheTrovate = new HashMap<GruppoSanguigno,Integer>();

		//Mappa in cui salvo la somma delle permanenze delle sacche trovate per tipo
		Map<GruppoSanguigno,Double> risultatoQuery = new HashMap<GruppoSanguigno,Double>();

		for(GruppoSanguigno g : GruppoSanguigno.values()) {
			risultatoQuery.put(g, 0.0);
			saccheTrovate.put(g, 0);
		}

		List<DatiSacca> listaDatiSacca = md.getListaDatiSacche();

		for (DatiSacca datiSacca : listaDatiSacca)
			//se la sacca è stata affidata (la query non vale per sacche non affidate)
			if (datiSacca.getDataAffidamento().isPresent()) {

				//calcolo quanto tempo è stata in magazzino
				days = (double) ChronoUnit.DAYS.between(datiSacca.getDataArrivo(), datiSacca.getDataAffidamento().get());

				//incremento il numero di sacche trovate di quel gruppo
				x = saccheTrovate.get(datiSacca.getGruppoSanguigno());
				x++;
				saccheTrovate.put(datiSacca.getGruppoSanguigno(), x);

				//incremento i giorni di permanenza per quel tipo
				y = risultatoQuery.get(datiSacca.getGruppoSanguigno());
				y += days;
				risultatoQuery.put(datiSacca.getGruppoSanguigno(), y);
			}

		//scorro la mappa e divido il contenuto per il numero di sacche per tipo, in modo da trovare la permanenza media
		for(GruppoSanguigno gs : risultatoQuery.keySet()) {
			if(saccheTrovate.get(gs) != 0) {
				y = risultatoQuery.get(gs);
				y = y / (saccheTrovate.get(gs));
				risultatoQuery.put(gs, y);
			}
		}
		return risultatoQuery;
	}

}	