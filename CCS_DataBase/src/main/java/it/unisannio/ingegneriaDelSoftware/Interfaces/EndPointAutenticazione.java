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
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.unisannio.ingegneriaDelSoftware.Exceptions.DipendenteNotFoundException;

public interface EndPointAutenticazione {

	/**Fa accedere al sistema un Dipedente identificato con Username e Password
     * @return Response
	 * @throws DipendenteNotFoundException 
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("username") String username,
                          @FormParam("password") String password);
	
    
    /**
     * Effettua il logout di un Dipendente, eliminando il token dell'utente dal server così che esso non sia più autenticato
     * @param header Intestazione che contiene il token da rimuovere per effettuare il logout
     */
    @DELETE
    @Path("/logout")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response logOut(@HeaderParam(HttpHeaders.AUTHORIZATION) String header);
	
    
    /**Modifica la password di un utente
     * @param password la nuova password
     * @param header il token di autentificazione
     */
    @PUT
    @Path("/cambiopassword")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response cambioPassword(@HeaderParam(HttpHeaders.AUTHORIZATION) String header, String password) throws AssertionError, DipendenteNotFoundException;
    
    
    /**Recupera la password di un Utente
     * @param username L'username dell'Utente che ha perso la password
     * @param cdf cdf dell'Utente che vuole recuperare la password
     */
    @PUT
    @Path("/recuperoPassword/{cdf}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response recuperoPassword(@PathParam("cdf")String cdf, String username) throws AssertionError, DipendenteNotFoundException;
}