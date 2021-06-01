package it.unisannio.ingegneriaDelSoftware.Interfaces;


import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;

import javax.ws.rs.core.Response;

public interface EndPointOperatoreCTT {

	/**Restituisce la Sacca del GruppoSanguigno richiesto con Data di scadenza più vicina nel DataBase locale.
	 * @param gruppoSanguigno Gruppo sanguigno ricercato
	 * @param dataArrivoMassima Data entro la quale la Sacca non deve scadere e deve arrivare all'Ente richiedente
	 * @param enteRichiedente Ente richiedente della Sacca
	 * @return la Sacca con le caratteristiche richieste
	 * @throws EntityNotFoundException Eccezione che si verifica quando la sacca inserita non viene trovata
	 */
	public Response ricercaSaccaLocale(String gruppoSanguigno,
									   String numeroSacche,
									   String dataArrivoMassima,
									   String enteRichiedente,
									   String indirizzoEnte,
									   String priorita) throws EntityNotFoundException, InterruptedException;
}
