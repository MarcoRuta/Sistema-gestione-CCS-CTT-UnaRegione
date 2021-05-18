package it.unisannio.ingegneriaDelSoftware.Interfaces;

import javax.ws.rs.core.Response;

import it.unisannio.ingegneriaDelSoftware.Exceptions.SaccaNotFoundException;

public interface EndPointOperatoreCTT {

	/**Restituisce la Sacca del GruppoSanguigno richiesto con Data di scadenza più vicina nel DataBase locale.
	 * @param gruppoSanguigno Gruppo sanguigno ricercato
	 * @param dataArrivoMassima Data entro la quale la Sacca non deve scadere e deve arrivare all'Ente richiedente
	 * @param enteRichiedente Ente richiedente della Sacca
	 * @return la Sacca con le caratteristiche richieste
	 * @throws SaccaNotFoundException Eccezione che si verifica quando la Sacca inserita non viene trovata
	 */
	Response ricercaSaccaLocale(String gruppoSanguigno, String dataArrivoMassima, String enteRichiedente, String indirizzoEnte) throws SaccaNotFoundException;
}
