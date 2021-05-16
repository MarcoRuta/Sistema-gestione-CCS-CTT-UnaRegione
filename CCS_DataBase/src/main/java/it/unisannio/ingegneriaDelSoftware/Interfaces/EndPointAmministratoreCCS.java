package it.unisannio.ingegneriaDelSoftware.Interfaces;


import java.util.List;

import javax.ws.rs.Consumes;
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
	
	/**
	 * Metodo utilizzato per aggiunta automatica dei CTT
	 * 
	 * @return serialiSacca*/
	@GET
	@Path("/centers")
	@Produces(MediaType.APPLICATION_JSON)
	List<CTT> listaCTT();

	

}
