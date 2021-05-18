package it.unisannio.ingegneriaDelSoftware.Interfaces;

import it.unisannio.ingegneriaDelSoftware.Classes.Seriale;

import javax.ws.rs.core.Response;
import java.util.List;

public interface EndPointMagazziniereCTT {

	/**Metodo attivato dal magazziniere quando riceve una notifica evasione Sacca esso aggiorna i datiSacca e rimuove la Sacca dal DB attivo
	 *
	 * @param seriale Seriale della Sacca da evadere
	 * @param ente_richiedente Ente che richiede la Sacca
	 * @param indirizzo Indirizzo dell'enteRichiedente
	 * @return Messaggio di errore in caso di problema di inserimento dati; Etichetta della Sacca in caso di successo
	 */
	public Response evasioneSacca(String seriale,
								  String ente_richiedente,
								  String indirizzo);

	/**Metodo con il quale il Magazziniere aggiunge una Sacca al DataBase
	 *
	 * @param gruppo_sanguigno Gruppo sanguigno della Sacca
	 * @param data_scadenza Data di scadenza della Sacca
	 * @param data_produzione Data di produzione della Sacca
	 * @param ente_donatore Ente di provenienza della Sacca
	 * @return Messaggio di errore in caso di problema di inserimento dati; Messaggio di corretta aggiunta in caso di successo
	 */
	public Response aggiuntaSaccaMagazzino(String gruppo_sanguigno,
										   String data_scadenza,
										   String data_produzione,
										   String ente_donatore);

	/**
	 * Metodo utilizzato per aggiunta automatica del seriale delle sacche
	 * disponibili nel magazzino per l'evasione
	 * @return serialiSacca
	 */
	public List<Seriale> listaSacca();
}