package it.unisannio.ingegneriaDelSoftware.Interfaces;

import java.time.LocalDate;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface MagazziniereCTTDataManager {

	/**@param username username del magazziniere che si logga nel sistema
	 * @param password  password del magazziniere che si logga nel sistema*/
	//boolean login(String username, String password);

	/**
	 * @param seriale seriale della sacca da evadere
	 * Metodo attivato dal magazziniere quando riceve una notifica evasione sacca
	 * esso aggiorna i dati sacca e rimuove la sacca dal DB attivo
	 * @return */
	@PUT
	@Path("/evasione/{seriale}")
	Response evasioneSacca(@QueryParam("seriale") String seriale, String ente_richiedente);

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
