package it.unisannio.ingegneriaDelSoftware.Interfaces;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche.NotificaEvasione;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriInfo;

public interface EndPointMagazziniereCTT {

	/**Metodo con il quale il Magazziniere aggiunge una Sacca al DataBase
	 * @param gruppo_sanguigno Gruppo sanguigno della Sacca
	 * @param data_scadenza Data di scadenza della Sacca
	 * @param data_produzione Data di produzione della Sacca
	 * @param ente_donatore Ente di provenienza della Sacca
	 * @param uriInfo info dell'uri relativo alla risorsa richiesta
	 * @return Response
	 * @throws EntityAlreadyExistsException se si vuole aggiungere una Sacca già presente nel DB
	 */
	@POST
	@Path("/aggiuntaSacca")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response aggiuntaSaccaMagazzino(@FormParam("gruppo_sanguigno") String gruppo_sanguigno,
										   @FormParam("data_scadenza") String data_scadenza,
										   @FormParam("data_produzione") String data_produzione,
										   @FormParam("ente_donatore") String ente_donatore,
										   @Context UriInfo uriInfo) throws EntityAlreadyExistsException;

	
	/**Metodo attivato dal magazziniere quando riceve una notifica evasione Sacca esso aggiorna i datiSacca e rimuove la Sacca dal DB attivo
	 * @param uriInfo info dell'uri relativo alla risorsa richiesta
	 * @return Response
	 * @throws EntityNotFoundException se la sacca da evadere non è presente nel DB
	 */
	@POST
	@Path("/evasione")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response evasioneSacca(NotificaEvasione notificaEvasione,
								  @Context UriInfo uriInfo) throws EntityNotFoundException;
	
	
	/**Ottiene i dati di una evasione sotto forma di PDF
	 * @param id_evasione id dell'evasione cercata
	 * @return StreamingOutput StreamingOutput da dove verrà aperto il pdf generato
	 * */
	@GET
	@Path("/evasione/pdf/{id_evasione}")
	@Produces("application/pdf")
	@Consumes(MediaType.TEXT_PLAIN)
	public StreamingOutput getPDF(@PathParam("id_evasione")String id_evasione);
	
}