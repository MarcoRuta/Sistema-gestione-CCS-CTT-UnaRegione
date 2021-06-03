package it.unisannio.ingegneriaDelSoftware.Interfaces;

import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaEvasione;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriInfo;

public interface EndPointMagazziniereCTT {

	/**Metodo attivato dal magazziniere quando riceve una notifica evasione Sacca esso aggiorna i datiSacca e rimuove la Sacca dal DB attivo
	 * @param uriInfo  info dell'uri relativo alla risorsa richiesta
	 * @return messaggio di corretta evasione.
	 * @throws EntityNotFoundException se la sacca da evadere non è presente nel DB
	 */
	public Response evasioneSacca(NotificaEvasione notificaEvasione,
								  UriInfo uriInfo) throws EntityNotFoundException;

	/**Metodo con il quale il Magazziniere aggiunge una Sacca al DataBase
	 *
	 * @param gruppo_sanguigno Gruppo sanguigno della Sacca
	 * @param data_scadenza Data di scadenza della Sacca
	 * @param data_produzione Data di produzione della Sacca
	 * @param ente_donatore Ente di provenienza della Sacca
	 * @param uriInfo  info dell'uri relativo alla risorsa richiesta
	 * @return Messaggio di errore in caso di problema di inserimento dati;
	 * @throws EntityAlreadyExistsException se si vuole aggiungere una sacca già presente nel DB
	 */
	public Response aggiuntaSaccaMagazzino(String gruppo_sanguigno,
										   String data_scadenza,
										   String data_produzione,
										   String ente_donatore,
										   UriInfo uriInfo) throws EntityAlreadyExistsException;

	/**
	 *Metodo che permette di ottenere i dati di una evasione sottoforma di PDF
	 * @param id_evasione id dell'evasione cercata
	 * @return StreamingOutput
	 * */
	public StreamingOutput getPDF(String id_evasione);
}