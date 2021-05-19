package it.unisannio.ingegneriaDelSoftware.Interfaces;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.unisannio.ingegneriaDelSoftware.Classes.CTT;


public interface EndPointAmministratoreCCS {
	
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
	@Path("/aggiuntaamministratore")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addDipendente(@FormParam("cdf")String cdf,
								  @FormParam("nome")String nome,
								  @FormParam("cognome")String cognome,
								  @FormParam("dataDiNascita")String dataDiNascita,
								  @FormParam("ruolo")String ruolo,
								  @FormParam("username")String username,
								  @FormParam("password")String password);

	/**Rimuove un Dipendente dal DataBase
	 * @param cdf Codice fiscale del Dipendente da rimuovere dal DataBase
	 * @return
	 */
	@DELETE
	@Path("/rimozioneamministratore/{cdf}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response removeDipendente(@PathParam("cdf") String cdf);
	
	/**
	 * @param seriale seriale della sacca da evadere
	 * Metodo attivato dal magazziniere quando riceve una notifica evasione sacca
	 * esso aggiorna i dati sacca e rimuove la sacca dal DB attivo
	 * @return */
	@PUT
	@Path("/evasione/{seriale}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	Response addCTT(@FormParam("numero") String numero,
		   		    @FormParam("denominazione") String denominazione,
		   	        @FormParam("provincia") String provincia,
		            @FormParam("citta") String citta, 
			        @FormParam("indirizzo") String indirizzo,
			        @FormParam("telefono") String telefono, 
				    @FormParam("email") String email,
				    @FormParam("latitude") String latitudine,
				    @FormParam("longitude") String longitudine);
	
	/**@return  messaggio corretta aggiunta sacca*/
	@POST
	@Path("/aggiuntaSacca")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	Response removeCTT(@PathParam("numero") String numero);	
	
	/**Metodo che restituisce la lista di tutti i CTT presenti nel database
	 * è un metodo che viene utilizzato per avere una lista dinamica nel RimuoviCTTForm.html attraverso JS
	 * 
	 * @return mm*/
	@GET
	@Path("/centers")
	@Produces(MediaType.APPLICATION_JSON)
	List<CTT> listaCTT();

	

}
