package it.unisannio.ingegneriaDelSoftware.Interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;

public interface EndPointSaccheInScadenzaCCS {

	
	/**Accetta una delle sacche in scadenza presenti nella rete
	 * L'iterazione del metodo termina con una richiesta di evasione presso il CTT che mantiene tale Sacca e una notifica di conferma per il CTT che l'ha richiesta
	 * @throws EntityNotFoundException 
	 */
	@DELETE
	@Path("/prenotaSaccaInScadenza/{seriale}")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response prenotaSacca(@PathParam("seriale") String seriale,
								 @QueryParam("enteRichiedente") String ente_richiedente,
								 @QueryParam("indirizzoEnte") String indirizzo) throws EntityNotFoundException;
	
	
	@DELETE
	@Path("/ritiroAlertCTT/{seriale}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response ritiroAlertCTTSacca(@PathParam("seriale") String seriale) throws EntityNotFoundException;
}