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

import java.util.List;

public interface EndPointSaccheInScadenzaCCS {

	/**Accetta una delle sacche in scadenza presenti nella rete
	 * L'iterazione del metodo termina con una richiesta di evasione presso il CTT che mantiene tale Sacca e una notifica di conferma per il CTT che l'ha richiesta
	 * @param seriale Seriale della sacca
	 * @param indirizzo Indirizzo ente richiedente
	 * @param ente_richiedente Ente richiedente
	 * @return Response
	 * @throws EntityNotFoundException
	 */
	@DELETE
	@Path("/prenotaSaccaInScadenza/{seriale}")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response prenotaSacca(@PathParam("seriale") String seriale,
								 @QueryParam("enteRichiedente") String ente_richiedente,
								 @QueryParam("indirizzoEnte") String indirizzo) throws EntityNotFoundException;

	/**Accetto il seriale di una sacca, precedentemente inviata al CCS perchè in scadenza, che è stata prenotata pressoil CTT mittente.
	 * La lista delle sacche in scadenza viene aggiornata ed inoltrata a tutti i ctt connessi
	 * @param seriale Seriale della sacca da prenotare
	 * @return Response
	 * @throws EntityNotFoundException
	 */
	@DELETE
	@Path("/ritiroAlertCTT/{seriale}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response ritiroAlertCTTSacca(@PathParam("seriale") String seriale) throws EntityNotFoundException;

	public void notifyCTT(List<Notifica> notifiche);
}