package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import java.net.MalformedURLException;
import java.net.URL;
import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import it.unisannio.ingegneriaDelSoftware.Annotazioni.Secured;
import it.unisannio.ingegneriaDelSoftware.Classes.CTT;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.CTTNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.EndPointAmministratoreCCS;


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
		
		MongoDataManager mm = new MongoDataManager();
		
		try{
			CTT ctt = new CTT(Integer.parseInt(numero),denominazione, provincia, citta, telefono, indirizzo, email, Double.parseDouble(latitudine), Double.parseDouble(longitudine));
			mm.createCTT(ctt);
			
			return Response
					// The source resource was successfully copied.
					// The COPY operation resulted in the creation of a new resource.
					.status(Response.Status.CREATED)
					.entity("CTT numero " + ctt.getNumero() + " aggiunto correttamente")
					.header("Location", new URL("http://127.0.0.1:8080/ctt/"+ctt.getNumero()))
					.build();
		}catch (AssertionError assertionError){
			//The request could not be understood by the server due to malformed syntax.
			// The client SHOULD NOT repeat the request without modifications.
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity("Dati non corretti, impossibile aggiungere la sacca al Magazzino\n"+assertionError.getMessage())
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
	
	
	/**Metodo attivato dall'Amministratore CCS nel momento in cui va rimosso un CTT dalla rete
	 * @param numero il numero identificativo del CTT che si vuole rimuovere
	 * @return Response */
	@DELETE
	@Path("/rimozioneCTT/{numero}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response removeCTT(@PathParam("numero") String numero) {
	try {
		MongoDataManager mm = new MongoDataManager();
		mm.removeCTT(Integer.parseInt(numero));
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
	


	/*
	 * *Metodo che aggiunge il dipendente al database
	 * 
	 * @param dip Dipendente da aggiungere
	 * 
	 */
	public void addDipendente(Dipendente dip) {
		MongoDataManager mm = new MongoDataManager();
		mm.addDipendente(dip);
		
	}
}
