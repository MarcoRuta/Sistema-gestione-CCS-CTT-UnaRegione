package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.*;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Beans.User;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Functional.IDGenerator;
import javax.annotation.security.PermitAll;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/autentificazione")
@PermitAll
@Singleton
public class EndPointRestAutentificazione {

    private MongoDataManager mm = MongoDataManager.getInstance();

    /**Login è l'operazione con la quale un generico Dipendente del CTT accede al sistema.
     * @param username Username dello user che intende autentificarsi
     * @param password Password dello user che intende autentificarsi
     * @return Response
     * @throws EntityNotFoundException se il login non va a buon fine
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("username") String username,
                          @FormParam("password") String password) throws EntityNotFoundException {
        Dipendente unDipendente = mm.getDipendente(username, password);
        String token =  Token.getToken(username + ":" + password).getValue();
        User aUser = new User(token,unDipendente.getRuolo().toString(),unDipendente.getNome(), unDipendente.getCognome(), unDipendente.getCdf().getCodiceFiscale());
        return Response.status(Response.Status.CREATED)
                .entity(aUser)
                .build();
    }

    /**Effettua il logout, esso elimina il token dell'utente dal server cosi che esso non sia piu autenticato
     * @param header Il token di autentificazione
     * @throws EntityNotFoundException se si sta facendo il logout con un token non valido
     */
    @DELETE
    @Path("/logout")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response logOut(@HeaderParam(HttpHeaders.AUTHORIZATION) String header)throws EntityNotFoundException{
            Token.removeToken(header.substring("Basic ".length()));
            return Response.status(Response.Status.OK)
                    .entity("Token rimosso correttamente")
                    .build();
    }

    /**Modifica la password di un utente
     * @param header Il token di autentificazione
     * @param cdf Il cdf dell'utente
     * @param password la nuova password
     * @return Response
     * @throws EntityNotFoundException,AssertionError,WebApplicationException se si vuole cambiare la password di un dipendente non registrato nel DB
     */
    @PUT
    @Path("/cambiopassword/{cdf}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response cambioPassword(@HeaderParam(HttpHeaders.AUTHORIZATION) String header,@PathParam("cdf")String cdf, String password) throws AssertionError, EntityNotFoundException {
        String requestToken = header.substring("Basic ".length());
        Dipendente dip = Token.getDipendenteByToken(requestToken);
        if (dip.getCdf().getCodiceFiscale().equals(cdf)) {
            mm.setPassword(dip.getCdf(), password);
            return Response.status(Response.Status.OK)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("Password cambiata correttamente")
                    .build();
        }
        throw  new WebApplicationException(Response.status(Response.Status.FORBIDDEN)
                .entity("Non puoi cambiare la password di un altro utente").type(MediaType.TEXT_PLAIN).build());

    }

    /**Recupera la password di un Utente
     * @param username L'username dell'Utente che ha perso la password
     * @param cdf cdf dell'Utente che vuole recuperare la password
     * @throws EntityNotFoundException,AssertionError,WebApplicationException se si vuole recuperare la password di un utente non presente nel DB
     */
    @PUT
    @Path("/recuperoPassword/{cdf}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response recuperoPassword(@PathParam("cdf")String cdf, String username) throws AssertionError, EntityNotFoundException {
        Dipendente dip = MongoDataManager.getInstance().getDipendente(Cdf.getCDF(cdf));
        String password = IDGenerator.getID();
        if (dip.getUsername().equals(username)) {
            mm.setPassword(dip.getCdf(), password);
            return Response.status(Response.Status.OK)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("La tua nuova password è: "+password)
                    .build();
        }
        throw  new WebApplicationException(Response.status(Response.Status.FORBIDDEN)
                .entity("username e codice fiscale non coincidono").type(MediaType.TEXT_PLAIN).build());
    }
}