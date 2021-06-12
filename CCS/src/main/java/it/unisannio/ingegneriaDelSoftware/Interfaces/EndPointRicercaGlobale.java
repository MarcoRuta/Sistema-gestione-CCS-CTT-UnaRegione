package it.unisannio.ingegneriaDelSoftware.Interfaces;

import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import javax.ws.rs.core.Response;

public interface EndPointRicercaGlobale {

    /**Restituisce la Sacca del GruppoSanguigno richiesto con Data di scadenza più vicina nel DataBase locale.
     * @param nome Nome del CTT richiedente
     * @param gruppoSanguigno Gruppo sanguigno ricercato
     * @param numeroSacche il numero di sacche richieste
     * @param dataArrivoMassima Data entro la quale la Sacca non deve scadere e deve arrivare all'Ente richiedente
     * @param enteRichiedente Ente richiedente della Sacca
     * @param indirizzoEnte Indirizzo dell'ente richiedente
     * @param priorita Ricerca prioritaria(true), ricerca non prioritaria(false)
     * @return Response
     * @throws InterruptedException, EntityNotFoundException
     */
    public Response ricercaSaccaGlobale(String nome, String gruppoSanguigno, String numeroSacche, String dataArrivoMassima, String enteRichiedente, String indirizzoEnte, String priorita) throws InterruptedException, EntityNotFoundException;
}
