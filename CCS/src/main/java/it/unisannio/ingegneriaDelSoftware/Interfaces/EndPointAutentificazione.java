package it.unisannio.ingegneriaDelSoftware.Interfaces;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;

public interface EndPointAutentificazione {

    /**Login è l'operazione con la quale un generico Dipendente del CTT accede al sistema.
     * @param username Username dello user che intende autentificarsi
     * @param password Password dello user che intende autentificarsi
     * @return Response
     * @throws EntityNotFoundException se il login non va a buon fine
     */
    public Response login(String username, String password) throws EntityNotFoundException;

    /**Effettua il logout, esso elimina il token dell'utente dal server cosi che esso non sia piu autenticato
     * @param header Il token di autentificazione
     * @throws EntityNotFoundException se si sta facendo il logout con un token non valido
     */
    public Response logOut(String header)throws EntityNotFoundException;

    
    /**Modifica la password di un utente
     * @param header Il token di autentificazione
     * @param cdf Il cdf dell'utente
     * @param password la nuova password
     * @return Response
     * @throws EntityNotFoundException,AssertionError,WebApplicationException se si vuole cambiare la password di un dipendente non registrato nel DB
     */
    public Response cambioPassword(String header,@PathParam("cdf")String cdf, String password) throws AssertionError, EntityNotFoundException;

    
    /**Recupera la password di un Utente
     * @param username L'username dell'Utente che ha perso la password
     * @param cdf cdf dell'Utente che vuole recuperare la password
     * @throws EntityNotFoundException,AssertionError,WebApplicationException se si vuole recuperare la password di un utente non presente nel DB
     */
    public Response recuperoPassword(String cdf, String username) throws AssertionError, EntityNotFoundException;
}