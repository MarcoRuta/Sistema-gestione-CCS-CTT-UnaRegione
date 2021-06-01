package it.unisannio.ingegneriaDelSoftware.EndPointRest;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.itextpdf.text.DocumentException;
import it.unisannio.ingegneriaDelSoftware.Annotazioni.Secured;
import it.unisannio.ingegneriaDelSoftware.CcsDataBaseRestApplication;
import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Functional.IDGenerator;
import it.unisannio.ingegneriaDelSoftware.Interfaces.EndPointAmministratoreCCS;
import it.unisannio.ingegneriaDelSoftware.PDF.PDFGenerator;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;

import java.time.LocalDate;
import java.time.format.*;

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
		CcsDataBaseRestApplication.logger.info("Si è richiesta l'aggiunta di un CTT");
		CTT ctt = new CTT(new CTTName(), provincia, citta, telefono, indirizzo, email, Double.parseDouble(latitudine), Double.parseDouble(longitudine));
		mm.createCTT(ctt);
		CcsDataBaseRestApplication.logger.info("il CTT è stato aggiunto correttamente al DataBase");

		// The source resource was successfully copied.
		// The COPY operation resulted in the creation of a new resource.
		return Response
				.status(Response.Status.OK)
				.entity(ctt.getDenominazione().getCttname() + " aggiunto correttamente")
				.header(HttpHeaders.LOCATION, uriInfo.getBaseUri()+"/centers/"+ctt.getDenominazione().getCttname())
				.build();
	}
	
	
	/**Rimuove un CTT dal Database dei CTT
	 * @return Response 
	 */
	@DELETE
	@Path("/rimozioneCTT/{cttName}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response removeCTT(@PathParam("cttName") String cttName) throws EntityNotFoundException, NumberFormatException {
		CcsDataBaseRestApplication.logger.info("Si è richiesta la rimozione di un CTT");
		mm.removeCTT(CTTName.getCttName(cttName));
		CcsDataBaseRestApplication.logger.info("Il CTT "+cttName+"è stato rimosso correttamente dal DB");
		return Response
				.status(Response.Status.OK)
				.entity(cttName + " rimosso correttamente")
				.build();
	}

	
	/**Restituisce la lista di tutti i CTT presenti nel Database dei CTT, utilizzato per l' aggiunta automatica dei CTT
	 * @return List<CTT> */
	@GET
	@Path("/centers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CTT> listaCTT(){
		CcsDataBaseRestApplication.logger.info("Si è richiesta la lista dei CTT presenti nel DataBase");
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
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addAmministratore(@FormParam("cdf")String cdf,
								  @FormParam("nome")String nome,
								  @FormParam("cognome")String cognome,
								  @FormParam("dataDiNascita")String dataDiNascita,
								  @FormParam("username")String username,
								  @Context UriInfo uriInfo) throws DateTimeParseException, IllegalArgumentException, AssertionError, EntityAlreadyExistsException {

		CcsDataBaseRestApplication.logger.info("Si è richiesta l'aggiunta di un' Amministratore");
		//creo un dipendente
		String password = IDGenerator.getID();
		Dipendente d = new Dipendente(Cdf.getCDF(cdf), nome, cognome,
				LocalDate.parse(dataDiNascita, DateTimeFormatter.ofPattern(Constants.DATEFORMAT)),
				RuoloDipendente.AmministratoreCCS, username, password);
		//aggiungo il dipendente al DB
		mm.createDipendente(d);
		CcsDataBaseRestApplication.logger.info("L'amministratore "+d.getCdf().getCodiceFiscale()+" è stato aggiunto correttamente al DataBase");
		return Response
				.status(Response.Status.CREATED)
				.entity("Dipendente aggiunto correttamente")
				.header(HttpHeaders.CONTENT_LOCATION,uriInfo.getAbsolutePath().getPath()+"/pdf/"+d.getCdf().getCodiceFiscale())
				.build();
	}

	
	/**Recupera un pdf con cdf, username e password di un Dipendente
	 * @param cdf il cdf del Dipendente di cui si vogliono recuperare i dati
	 * @return StreamingOutput
	 * */
	@GET
	@Path("aggiuntaAmministratore/pdf/{cdf}")
	@Produces("application/pdf")
	@Consumes(MediaType.TEXT_PLAIN)
	public StreamingOutput getPDF(@PathParam("cdf")String cdf){
		CcsDataBaseRestApplication.logger.info("Sono stati richiesti i dati Del Dipendente: "+cdf);
		return new StreamingOutput() {
			public void write(OutputStream output){
				try {
					Dipendente dip = mm.getDipendente(Cdf.getCDF(cdf));
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

	
	/**Rimuove un Dipendente dal DataBase
	 * @param cdf Codice fiscale del Dipendente da rimuovere dal DataBase
	 * @return Response
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
	 * @return List<Dipendente> Lista di tutti i Dipendenti
	 */
	@GET
	@Path("/amministratori")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Dipendente> getAmministratori(@HeaderParam(HttpHeaders.AUTHORIZATION) String header) throws EntityNotFoundException {
		CcsDataBaseRestApplication.logger.info("Si è richiesta la lista degli amministratori presenti nel DB");
		List<Dipendente> dipendenti = mm.getListaDipendenti();
		dipendenti.remove(Token.getDipendenteByToken(header.substring("Basic ".length())));
		return dipendenti;
	}
}