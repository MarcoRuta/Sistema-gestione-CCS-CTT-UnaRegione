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
import it.unisannio.ingegneriaDelSoftware.Classes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManagerBean;
import it.unisannio.ingegneriaDelSoftware.Interfaces.EndPointAmministratoreCCS;
import it.unisannio.ingegneriaDelSoftware.Util.DateUtil;

import java.time.format.*;

@Path("/CCS")
@Singleton
@Secured
@RolesAllowed("AmministratoreCCS")
public class EndPointRestAmministratoreCCS implements EndPointAmministratoreCCS{

	/**Metodo attivato dall'Amministratore CCS nel momento in cui va aggiunto un nuovo CTT alla rete
	 * @param numero il numero identificativo del CTT che si vuole inserire
	 * @param denominazione la denominazione del CTT che si vuole inserire
	 * @param provincia la provincia del CTT che si vuole inserire
	 * @param citta la citta del CTT che si vuole inserire
	 * @param indirizzo l'indirizzo del CTT che si vuole aggiungere
	 * @param telefono il telefono del CTT che si vuole aggiungere
	 * @param email l'e-mail del CTT che si vuole aggiungere
	 * @param latitudine la latidudine del CTT che si vuole aggiungere
	 * @param longitudine la longitudine del CTT che si vuole aggiungere
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
			MongoDataManagerBean.createCTT(ctt);
			
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
					.entity("Non � stato possibile creare l'uri per la nuova risorsa")
					.build();
		}
	}
	
	
	/**Metodo attivato dall'Amministratore CCS nel momento in cui va rimosso un CTT dalla rete
	 * @param numero il numero identificativo del CTT che si vuole rimuovere
	 * @return Response */
	@DELETE
	@Path("/rimozioneCTT/{numero}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response removeCTT(@PathParam("numero") String numero) {
	try {
		MongoDataManagerBean.removeCTT(Integer.parseInt(numero));
		return Response
				.status(Response.Status.OK)
				.entity("CTT numero " + numero + " rimosso correttamente")
				.build();
	}catch(AssertionError e){
		//The request could not be understood by the server due to malformed syntax.
		// The client SHOULD NOT repeat the request without modifications.
		return Response
				.status(Response.Status.BAD_REQUEST)
				.entity(e.getMessage())
				.build();
		}
	
	}

	
	/**
	 * Metodo utilizzato per aggiunta automatica dei CTT
	 * 
	 * @return */
	@GET
	@Path("/centers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CTT> listaCTT(){
		
		return MongoDataManagerBean.getListaCTT();	

	}
	
	
	/**Metodo attivato dall'ammministratore quando deve essere aggiunto un altro amministratore nel sistemaCCS
	 * @param cdf Dipendente da aggiungere al DataBase
	 * @param nome Dipendente da aggiungere al DataBase
	 * @param cognome Dipendente da aggiungere al DataBase
	 * @param dataDiNascita del Dipendente da aggiungere al DataBase
	 * @param ruolo del Dipendente da aggiungere al DataBase
	 * @param username del Dipendente da aggiungere al DataBase
	 * @param password del Dipendente da aggiungere al DataBase
	 * @return Response 
	 */
	@POST
	@Path("/aggiuntaAmministratore")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addDipendente(@FormParam("cdf")String cdf,
								  @FormParam("nome")String nome,
								  @FormParam("cognome")String cognome,
								  @FormParam("dataDiNascita")String dataDiNascita,
								  @FormParam("ruolo")String ruolo,
								  @FormParam("username")String username,
								  @FormParam("password")String password){

		try {
			//creo un dipendente
			Dipendente d = new Dipendente(new Cdf(cdf), nome, cognome, DateUtil.dateParser(dataDiNascita),
					RuoloDipendente.valueOf(ruolo), username, password);
			//aggiungo il dipendente al DB
			MongoDataManagerBean.createDipendente(d);
			return Response
					.status(Response.Status.OK)
					.entity("Dipendente aggiunto: "+cdf)
					.build();
		}catch(DateTimeParseException| IllegalArgumentException | AssertionError e) {
			//The request could not be understood by the server due to malformed syntax.
			// The client SHOULD NOT repeat the request without modifications.
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(e.getMessage())
					.build();
		}
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
			MongoDataManagerBean.removeDipendente(Cdf.getCDF(cdf));
			return Response
					.status(Response.Status.OK)
					.entity("Dipendente rimosso: " + cdf)
					.build();
		}catch (AssertionError ex){
			//il dipendente che si vuole rimuovere non � presente
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity("Rimozione del Dipendente non riuscita")
					.build();
		}
	}


	/**
	 * Metodo utilizzato per aggiunta automatica dei dipendenti
	 * 
	 * @return */
	@GET
	@Path("/dipendenti")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Dipendente> listaDip(){
		
		return MongoDataManagerBean.getListaDipendenti();	

	}
		
}