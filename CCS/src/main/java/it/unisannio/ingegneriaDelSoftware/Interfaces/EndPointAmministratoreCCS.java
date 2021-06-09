package it.unisannio.ingegneriaDelSoftware.Interfaces;

import java.time.format.DateTimeParseException;
import java.util.List;

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

import it.unisannio.ingegneriaDelSoftware.Classes.CTT;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;

public interface EndPointAmministratoreCCS {
	
	/**Aggiunge un nuovo CTT al Database dei CTT
	 * @param provincia La provincia del CTT che si vuole inserire
	 * @param citta La città del CTT che si vuole inserire
	 * @param indirizzo L'indirizzo del CTT che si vuole aggiungere
	 * @param telefono Il telefono del CTT che si vuole aggiungere
	 * @param email L'email del CTT che si vuole aggiungere
	 * @param latitudine La latitudine del CTT che si vuole aggiungere
	 * @param longitudine La longitudine del CTT che si vuole aggiungere
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
						   @Context UriInfo uriInfo) throws EntityAlreadyExistsException ;
	
	
	/**Rimuove un CTT dal Database dei CTT
	 * @return Response 
	 */
	@DELETE
	@Path("/rimozioneCTT/{cttName}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response removeCTT(@PathParam("cttName") String cttName) throws EntityNotFoundException, NumberFormatException;
	
	
	/**Restituisce la lista di tutti i CTT presenti nel Database dei CTT, utilizzato per l' aggiunta automatica dei CTT
	 * @return List<CTT> */
	@GET
	@Path("/centers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CTT> listaCTT();
	
	
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
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addAmministratore(@FormParam("cdf")String cdf,
								  @FormParam("nome")String nome,
								  @FormParam("cognome")String cognome,
								  @FormParam("dataDiNascita")String dataDiNascita,
								  @FormParam("username")String username,
								  @Context UriInfo uriInfo) throws DateTimeParseException, IllegalArgumentException, AssertionError, EntityAlreadyExistsException;

	
	/**Recupera un pdf con cdf, username e password di un Dipendente
	 * @param cdf il cdf del Dipendente di cui si vogliono recuperare i dati
	 * @return StreamingOutput
	 */
	@GET
	@Path("aggiuntaAmministratore/pdf/{cdf}")
	@Produces("application/pdf")
	@Consumes(MediaType.TEXT_PLAIN)
	public StreamingOutput getPDF(@PathParam("cdf")String cdf);
	
	
	/**Rimuove un Dipendente dal DataBase
	 * @param cdf Codice fiscale del Dipendente da rimuovere dal DataBase
	 * @return Response
	 */
	@DELETE
	@Path("/rimozioneAmministratore/{cdf}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response removeAmministratore(@HeaderParam(HttpHeaders.AUTHORIZATION)String header,
									 @PathParam("cdf") String cdf) throws EntityNotFoundException;
	
	
	/**Restituisce la lista di tutti i Dipendenti presenti nel database dei Dipendenti
	 * @return List<Dipendente> Lista di tutti i Dipendenti
	 */
	@GET
	@Path("/amministratori")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Dipendente> getAmministratori(@HeaderParam(HttpHeaders.AUTHORIZATION) String header) throws EntityNotFoundException;

	/**---------REPORT DIPENDENTI RETE CTT------------
	 * Restituisce la lista dei Dipendenti di tutti i CTT presenti sulla rete del ruolo selezionato
	 * @param ruolo Ruolo dei Dipendenti da cercare
	 * @return Response 200 OK e invia la lista dei dipendenti del ruolo selezionato
	 */
	@GET
	@Path("/reportDipendentiCCS")
	@Produces(MediaType.APPLICATION_JSON)
	public Response reportDipendentiCCS(@QueryParam("ruolo")String ruolo);
	
	
	/**---------REPORT NUMERICO DEI TIPI DI SACCHE PRESENTI A LIVELLO REGIONALE------------
	 * Restituisce il numero di sacche presenti di ogni tipo nella regione
	 * @return Response 200 OK e invia una mappa <gs,numeroSacche>
	 * @return 400 BAD_REQUEST se i parametri inseriti non sono corretti
	 */
	@GET
	@Path("/reportStatisticoSaccheCCS")
	@Produces(MediaType.APPLICATION_JSON)
	public Response reportStatisticoSaccheRegionale(@HeaderParam(HttpHeaders.AUTHORIZATION) String headers);
	
	
	/**---------REPORT SACCHE INVIATE RETE REGIONALE------------
	 *Restituisce la lista dei DatiSacche relativi alle sacche che sono state affidate in un determinato arco temporale
	 * 	 * @param dataInizio Data inizio dell' arco temporale
	 * 	 * @param dataFine Data fine dell' arco temporale
	 * 	 * @return Response 200 OK e invia la lista dei datiSacca
	 * 	 * @return 400 BAD_REQUEST se i parametri inseriti non sono corretti
	 */
	@GET
	@Path("/reportSaccheInviateCCS")
	@Produces(MediaType.APPLICATION_JSON)
	public Response reportSaccheInviate(@QueryParam("dataInizio")String dataInizio,
										 @QueryParam("dataFine")String dataFine) throws DateTimeParseException;
	
	
	/**---------REPORT SACCHE RICEVUTE RETE REGIONALE------------
	 *Restituisce la lista dei DatiSacche relativi alle sacche che sono state ricevute dalla rete in un determinato arco temporale
	 * 	 * @param dataInizio Data inizio dell' arco temporale
	 * 	 * @param dataFine Data fine dell' arco temporale
	 * 	 * @return Response 200 OK e invia la lista dei datiSacca
	 * 	 * @return 400 BAD_REQUEST se i parametri inseriti non sono corretti
	 */
	@GET
	@Path("/reportSaccheRicevuteCCS")
	@Produces(MediaType.APPLICATION_JSON)
	public Response reportSaccheRicevute(@QueryParam("dataInizio")String dataInizio,
										@QueryParam("dataFine")String dataFine) throws DateTimeParseException;
	
	
	/**---------REPORT PERMANENZA MEDIA PER TIPO DI SANGUE A LIVELLO REGIONALE------------
	 * Restituisce il numero di sacche presenti di ogni tipo nella regione
	 * @return Response 200 OK e invia una mappa <gs,numeroSacche>
	 * @return 400 BAD_REQUEST se i parametri inseriti non sono corretti
	 */
	@GET
	@Path("/giacenzaMediaSaccheCCS")
	@Produces(MediaType.APPLICATION_JSON)
	public Response giacenzaMediaSaccheRegionale(@HeaderParam(HttpHeaders.AUTHORIZATION) String headers);
	
	
	/**Restituisce una mappa di <CTTName,boolean>, true se il CTT è online
	 * @return Response 200 OK e invia una mappa <CTTName,boolean>
	 * @return 400 BAD_REQUEST se i parametri inseriti non sono corretti
	 */
	@GET
	@Path("/statusReteCtt")
	@Produces(MediaType.APPLICATION_JSON)
	public Response statusReteCtt(@HeaderParam(HttpHeaders.AUTHORIZATION) String headers);
}