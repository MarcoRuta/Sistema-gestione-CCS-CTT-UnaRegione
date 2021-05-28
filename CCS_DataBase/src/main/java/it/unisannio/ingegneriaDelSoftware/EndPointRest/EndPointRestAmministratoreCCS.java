package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.unisannio.ingegneriaDelSoftware.Annotazioni.Secured;
import it.unisannio.ingegneriaDelSoftware.Classes.CTT;
import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.PasswordGenerator;
import it.unisannio.ingegneriaDelSoftware.Classes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.CTTNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.DipendenteNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.EndPointAmministratoreCCS;
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
	 * @param numero Il numero identificativo del CTT che si vuole inserire
	 * @param denominazione La denominazione del CTT che si vuole inserire
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
	public Response addCTT(@FormParam("numero_ctt") String numero,
			   		   @FormParam("nome_ctt") String denominazione,
			   	       @FormParam("provincia") String provincia,
			           @FormParam("citta") String citta, 
				       @FormParam("indirizzo") String indirizzo,
				       @FormParam("telefono") String telefono, 
					   @FormParam("email") String email,
					   @FormParam("latitude") String latitudine,
					   @FormParam("longitude") String longitudine)
						{
		
		try{
			CTT ctt = new CTT(Integer.parseInt(numero),denominazione, provincia, citta, telefono, indirizzo, email, Double.parseDouble(latitudine), Double.parseDouble(longitudine));		
			mm.createCTT(ctt);
			
			return Response
					// The source resource was successfully copied.
					// The COPY operation resulted in the creation of a new resource.
					.status(Response.Status.OK)
					.entity("CTT numero " + ctt.getNumero() + " aggiunto correttamente")
					.header("Location", new URL("http://127.0.0.1:8080/ctt/"+ctt.getNumero()))
					.build();
		}catch (AssertionError assertionError){
			//The request could not be understood by the server due to malformed syntax.
			// The client SHOULD NOT repeat the request without modifications.
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(assertionError.getMessage())
					.build();
		} catch (MalformedURLException e) {
			//something has gone wrong on the website's server,
			// but the server could not be more specific on what the exact problem is.
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Non è stato possibile creare l'uri per la nuova risorsa")
					.build();
		}
	}
	
	
	/**Rimuove un CTT dal Database dei CTT
	 * @param numero Il numero identificativo del CTT che si vuole rimuovere
	 * @return Response */
	@DELETE
	@Path("/rimozioneCTT/{numero}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response removeCTT(@PathParam("numero") String numero) {
	try {
		mm.removeCTT(Integer.parseInt(numero));
		return Response
				.status(Response.Status.OK)
				.entity("CTT numero " + numero + " rimosso correttamente")
				.build();
		}catch(AssertionError | NumberFormatException e){
		//The request could not be understood by the server due to malformed syntax.
		// The client SHOULD NOT repeat the request without modifications.
		return Response
				.status(Response.Status.BAD_REQUEST)
				.entity(e.getMessage())
				.build();
		} catch (CTTNotFoundException e) {
		return Response
				.status(Response.Status.NOT_FOUND)
				.entity(e.getMessage())
				.build();
		}	
	}

	
	/**Restituisce la lista di tutti i CTT presenti nel Database dei CTT, utilizzato per l' aggiunta automatica dei CTT
	 * @return List<CTT> */
	@GET
	@Path("/centers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CTT> listaCTT(){
		return mm.getListaCTT();	
	}
	
	
	/**Aggiunge un AmministratoreCCS nel Database dei Dipendenti
	 * @param cdf Codice fiscale del Dipendente da aggiungere al DataBase
	 * @param nome Nome del Dipendente da aggiungere al DataBase
	 * @param cognome Cognome del Dipendente da aggiungere al DataBase
	 * @param dataDiNascita Data di nascita del Dipendente da aggiungere al DataBase
	 * @param ruolo Ruolo del Dipendente da aggiungere al DataBase
	 * @param username Username del Dipendente da aggiungere al DataBase
	 * @return Response 
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
								  @FormParam("username")String username) throws DateTimeParseException,IllegalArgumentException,AssertionError{

		//creo un dipendente
		String password = PasswordGenerator.getPassword();
		Dipendente d = new Dipendente(Cdf.getCDF(cdf), nome, cognome,
				LocalDate.parse(dataDiNascita, DateTimeFormatter.ofPattern(Constants.DATEFORMAT)),
				RuoloDipendente.valueOf(ruolo), username, password);
		//aggiungo il dipendente al DB
		mm.createDipendente(d);
		return Response
				.status(Response.Status.OK)
				.entity("Dipendente: "+cdf+"\n"+"username: "+username+"\n"+"password: "+password)
				.build();
	}

	
	/**Rimuove un Dipendente dal DataBase
	 * @param cdf Codice fiscale del Dipendente da rimuovere dal DataBase
	 * @return
	 */
	@DELETE
	@Path("/rimozioneAmministratore/{cdf}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response removeDipendente(@PathParam("cdf") String cdf) {
		try {
			//rimuovo il dipendente dal ctt
			mm.removeDipendente(Cdf.getCDF(cdf));
			return Response
					.status(Response.Status.OK)
					.entity("Dipendente rimosso: " + cdf)
					.build();
		}catch (AssertionError | DipendenteNotFoundException ex){
			//il dipendente che si vuole rimuovere non è presente
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity("Rimozione del Dipendente non riuscita")
					.build();
		}
	}


    /**
	 * Restituisce la lista di tutti i Dipendenti presenti nel database dei Dipendenti
	 * @return List<Dipendente> Lista di tutti i Dipendenti
	 */
	@GET
	@Path("/dipendenti")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Dipendente> listaDip(){
		
		return mm.getListaDipendenti();	
	}	
}