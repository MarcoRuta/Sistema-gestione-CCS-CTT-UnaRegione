package it.unisannio.ingegneriaDelSoftware.Interfaces;

import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
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
    @GET
    @Path("/ricercaGlobale")
    public Response ricercaSaccaGlobale(@QueryParam("nome") String nome,
                                        @QueryParam("gruppo") String gruppoSanguigno,
                                        @QueryParam("numero") String numeroSacche,
                                        @QueryParam("dataArrivoMassima") String dataArrivoMassima,
                                        @QueryParam("enteRichiedente") String enteRichiedente,
                                        @QueryParam("indirizzoEnte") String indirizzoEnte,
                                        @QueryParam("priorità") String priorita) throws InterruptedException, EntityNotFoundException;
}
