package it.unisannio.ingegneriaDelSoftware.Interfaces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;

public interface EndPointAutentificazione {

	/**Login è l'operazione con la quale un generico Dipendente del CTT accede al sistema
     * @return Response
     * esso setta il token di autentificazione nei cookie con cookieName = "access_token
     * il cookie ha una max-Age pari ad un turno lavorativo di 8 ore"
     * @throws  EntityNotFoundException se il login non va a buon fine
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("username") String username,
                          @FormParam("password") String password) throws EntityNotFoundException;

    /**Effettua il logout, esso elimina il token dell'utente dal server cosi che esso non sia piu autenticato
     * @param header da rimuovere per effettuare il logout
     * @throws EntityNotFoundException se si sta facendo il logout con un token non valido
     */
    @DELETE
    @Path("/logout")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response logOut(@HeaderParam(HttpHeaders.AUTHORIZATION) String header)throws EntityNotFoundException;

    
    /**Modifica la password di un utente
     * @param password la nuova password
     * @param header il token di autentificazione
     * @throws EntityNotFoundException se si vuole cambiare la password di un dipendente non registrato nel DB
     */
    @PUT
    @Path("/cambiopassword/{cdf}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response cambioPassword(@HeaderParam(HttpHeaders.AUTHORIZATION) String header,@PathParam("cdf")String cdf, String password) throws AssertionError, EntityNotFoundException;
}