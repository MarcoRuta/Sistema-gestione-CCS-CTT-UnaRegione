package it.unisannio.ingegneriaDelSoftware.Interfaces;


import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface EndPointOperatoreCTT {

	
	/**Restituisce la Sacca del GruppoSanguigno richiesto con Data di scadenza più vicina nel DataBase locale.
	 * @param gruppoSanguigno Gruppo sanguigno ricercato
	 * @param numeroSacche il numero di sacche richieste
	 * @param dataArrivoMassima Data entro la quale la Sacca non deve scadere e deve arrivare all'Ente richiedente
	 * @param enteRichiedente Ente richiedente della Sacca
	 * @return Response
	 */
	public Response ricercaSaccaLocale(String gruppoSanguigno, String numeroSacche, String dataArrivoMassima,String enteRichiedente, String indirizzoEnte, String priorita) throws InterruptedException;
	
	
	/**Alert della prenotazione della Sacca
	 * @param seriale Il Seriale della Sacca
	 * @param indirizzoEnte L'indirizzo dell'ente che ha richiesto la Sacca
	 * @param enteRichiedente L'ente che ha richiesto la Sacca
	 * @return Response
	 */
	public Response prenotaSaccaAlert(String seriale, String indirizzoEnte, String enteRichiedente);
	
	
}