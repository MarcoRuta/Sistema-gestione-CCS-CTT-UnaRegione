package it.unisannio.ingegneriaDelSoftware.Interfaces;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriInfo;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;

import java.time.format.DateTimeParseException;
import java.util.List;


public interface EndPointAmministratoreCTT {

	/**Aggiunge un Dipendente al DataBase
	 * @param cdf Dipendente da aggiungere al DataBase
	 * @param nome Il nome del Dipendente
	 * @param cognome Il cognome del Dipendente
	 * @param dataDiNascita La data di nascita del Dipendente
	 * @param ruolo Il ruolo del Dipendente
	 * @param username L'username del Dipendente
	 * @return Response
	 * @throws DateTimeParseException, IllegalArgumentException, AssertionError, EntityAlreadyExistsException
	 */
	@POST
	@Path("/aggiuntaDipendente")
	@Produces("application/pdf")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addDipendente(@FormParam("cdf")String cdf,
								  @FormParam("nome")String nome,
								  @FormParam("cognome")String cognome,
								  @FormParam("dataDiNascita")String dataDiNascita,
								  @FormParam("ruolo")String ruolo,
								  @FormParam("username")String username) throws DateTimeParseException, IllegalArgumentException, AssertionError, EntityAlreadyExistsException;


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
									 @PathParam("cdf") String cdf) throws EntityNotFoundException;


	/*---------REPORT OPERATORI CTT------------
	 /**Restituisce la lista dei Dipendenti del CTT che occupano il Ruolo scelto
	 * @param ruolo Ruolo dei Dipendenti da cercare
	 * @return Response
	 */
	@GET
	@Path("/reportDipendentiCtt")
	@Produces(MediaType.APPLICATION_JSON)
	public Response reportDipendentiCTT(@QueryParam("ruolo")String ruolo);


	 /**Restituisce il numero di Sacche presenti di ogni gruppo sanguigno nel database delle Sacche
	  * @return Response 
	  */
	@GET
	@Path("/reportStatisticoSacche")
	@Produces(MediaType.APPLICATION_JSON)
	public Response reportStatisticoSacche(@HeaderParam(HttpHeaders.AUTHORIZATION) String headers);

	
	/**Restituisce la lista dei DatiSacche relativi alle sacche che sono state affidate in un determinato arco temporale
	 * @param dataInizio Data inizio dell' arco temporale
	 * @param dataFine Data fine dell' arco temporale
	 * @return Response
	 */
	@GET
	@Path("/reportLocaleSaccheInviate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response reportLocaleSaccheInviate(@QueryParam("dataInizio")String dataInizio,
											  @QueryParam("dataFine")String dataFine) throws DateTimeParseException;

	
	/**Restituisce la lista dei DatiSacche relativi alle sacche che sono state ricevute in un determinato arco temporale
	 * @param dataInizio Data inizio dell' arco temporale
	 * @param dataFine Data fine dell' arco temporale
	 * @return Response
	 */
	@GET
	@Path("/reportLocaleSaccheRicevute")
	@Produces(MediaType.APPLICATION_JSON)
	public Response reportLocaleSaccheRicevute(@QueryParam("dataInizio")String dataInizio,
											   @QueryParam("dataFine")String dataFine) throws DateTimeParseException;

	
	/**Calcola quanto è il tempo medio di giacenza delle Sacche di sangue all'interno del magazzino
	 * @return Response
	 */
	@GET
	@Path("/giacenzaMediaSacche")
	@Produces(MediaType.APPLICATION_JSON)
	public Response giacenzaMedia();

	
	/**Restituisce la lista di Dipendenti contenuti nel database dei Dipendenti 
	 * @return la lista di Dipendenti che lavorano al CTT
	 * @throws EntityNotFoundException*/
	@GET
	@Path("/dipendenti")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Dipendente> getDipendenti(@HeaderParam(HttpHeaders.AUTHORIZATION) String header) throws EntityNotFoundException;


}