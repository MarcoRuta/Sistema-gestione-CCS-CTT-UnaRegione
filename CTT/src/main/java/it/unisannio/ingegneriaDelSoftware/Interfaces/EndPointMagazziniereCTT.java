package it.unisannio.ingegneriaDelSoftware.Interfaces;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriInfo;

public interface EndPointMagazziniereCTT {

	/**Metodo attivato dal magazziniere quando riceve una notifica evasione Sacca esso aggiorna i datiSacca e rimuove la Sacca dal database attivo
	 * @param seriale Seriale della Sacca da evadere
	 * @param ente_richiedente Ente che richiede la Sacca
	 * @param indirizzo Indirizzo dell'enteRichiedente
	 * @param uriInfo  info dell'uri relativo alla risorsa richiesta
	 * @return Response Una risposta diversa in base al soddisfacimento o meno della richiesta
	 * @throws EntityNotFoundException se la sacca da evadere non è presente nel DB
	 */
	public Response evasioneSacca(String seriale,
								  String ente_richiedente,
								  String indirizzo,
								  UriInfo uriInfo) throws EntityNotFoundException;

	/**Metodo con il quale il Magazziniere aggiunge una Sacca al DataBase
	 * @param gruppo_sanguigno Gruppo sanguigno della Sacca
	 * @param data_scadenza Data di scadenza della Sacca
	 * @param data_produzione Data di produzione della Sacca
	 * @param ente_donatore Ente di provenienza della Sacca
	 * @param uriInfo  info dell'uri relativo alla risorsa richiesta
	 * @return Response Una risposta diversa in base al soddisfacimento o meno della richiesta
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