package it.unisannio.ingegneriaDelSoftware.Interfaces;

import javax.ws.rs.core.Response;

import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;

public interface EndPointAutentificazione {

	/**Login è l'operazione con la quale un generico Dipendente del CTT accede al sistema
     * @return Response
     * esso setta il token di autentificazione nei cookie con cookieName = "access_token
     * il cookie ha una max-Age pari ad un turno lavorativo di 8 ore"
     * @throws  EntityNotFoundException se il login non va a buon fine
     */
    public Response login(String username, String password) throws EntityNotFoundException;

    /**Effettua il logout, esso elimina il token dell'utente dal server cosi che esso non sia piu autenticato
     * @param header da rimuovere per effettuare il logout
     * @throws EntityNotFoundException se si sta facendo il logout con un token non valido
     */
    public Response logOut(String header)throws EntityNotFoundException;

    
    /**Modifica la password di un utente
     * @param password la nuova password
     * @param header il token di autentificazione
     * @throws EntityNotFoundException se si vuole cambiare la password di un dipendente non registrato nel DB
     */
    public Response cambioPassword(String header,String cdf, String password) throws AssertionError, EntityNotFoundException;
}