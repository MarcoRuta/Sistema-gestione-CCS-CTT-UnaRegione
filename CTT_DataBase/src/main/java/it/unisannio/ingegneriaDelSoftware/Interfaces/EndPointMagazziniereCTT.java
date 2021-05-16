package it.unisannio.ingegneriaDelSoftware.Interfaces;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface EndPointMagazziniereCTT {

	/**@param username Username del magazziniere che si logga nel sistema
	 * @param password Password del magazziniere che si logga nel sistema*/
	//boolean login(String username, String password);

	/**
	 * @param seriale Seriale della sacca da evadere
	 * Metodo attivato dal magazziniere quando riceve una notifica evasione sacca
	 * esso aggiorna i dati sacca e rimuove la sacca dal DB attivo
	 * @return */
	@PUT
	@Path("/evasione/{seriale}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response evasioneSacca(@PathParam("seriale") String seriale,
								  @FormParam("enterichiedente") String ente_richiedente,
								  @FormParam("indirizzo")String indirizzo);

	/**@return  messaggio corretta aggiunta sacca*/
	@POST
	@Path("/aggiuntaSacca")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response aggiuntaSaccaMagazzino(@FormParam("gruppo_sanguigno") String gruppo_sanguigno,
										   @FormParam("data_scadenza") String data_scadenza,
										   @FormParam("data_produzione") String data_produzion,
										   @FormParam("ente_donatore") String ente_donatore);
}