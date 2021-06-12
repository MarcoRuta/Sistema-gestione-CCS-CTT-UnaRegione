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
	public Response aggiuntaSaccaMagazzino(String gruppo_sanguigno, String data_scadenza, String data_produzione, String ente_donatore, @Context UriInfo uriInfo) throws EntityAlreadyExistsException;

	
	/**Metodo attivato dal magazziniere quando riceve una notifica evasione Sacca esso aggiorna i datiSacca e rimuove la Sacca dal DB attivo
	 * @return Response
	 * @throws EntityNotFoundException se la sacca da evadere non è presente nel DB
	 */
	public Response evasioneSacca(NotificaEvasione notificaEvasione) throws EntityNotFoundException;
	
	

	
}