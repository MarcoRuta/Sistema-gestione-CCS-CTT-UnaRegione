package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.Token;
import it.unisannio.ingegneriaDelSoftware.Classes.User;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.DipendenteNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.TokenNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.DataManager;
import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/autentificazione")
@PermitAll
public class EndPointRestAutentificazione {

    /**
     * Login è l'operazione con la quale un generico dipendente del ctt accede al sistema
     *
     * @return il ruolo con il quale si è registrati
     * esso setta il token di autentificazione nei cookie con cookieName = "access_token
     * il cookie ha una max-Age pari ad un turno lavorativo di 8 ore"
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("username") String username,
                          @FormParam("password") String password) {
        try {
            DataManager mm = new MongoDataManager();
            Dipendente unDipendente = mm.getDipendente(username, password);
            String token =  Token.getToken(username + ":" + password).getValue();
            //Cookie aCookie = new Cookie("access_token", Token.getToken(username + ":" + password).getValue());
            User aUser = new User(token,unDipendente.getRuolo().toString(),unDipendente.getNome(), unDipendente.getCognome());
            return Response.status(Response.Status.OK)
                    .entity(aUser)
                    //.cookie(new NewCookie(aCookie, "token di accesso", 60 * 60 * 8, false))
                    .build();
        } catch (DipendenteNotFoundException exp) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("username o password errati!")
                    .build();
        }
    }

    /**
     * Metodo che effettua il logout, esso elimina il token dell'utente dal server cosi che esso non sia piu autenticato
     *
     * @param header da rimuovere per effetuare il logout
     */
    @DELETE
    @Path("/logout")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response logOut(@HeaderParam(HttpHeaders.AUTHORIZATION) String header) {
        try {
            Token.removeToken(header.substring("Basic ".length()));
            return Response.status(Response.Status.OK)
                    .entity("Token rimosso correttamente")
                    .build();
        } catch (TokenNotFoundException exp) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Impossibile rimuovere il Token, " + exp.getMessage())
                    .build();
        }
    }


    @PUT
    @Path("/cambiopassword/{cdf}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response cambioPassword(@PathParam("cdf")String cdf, String password){
        try{
            System.out.println("richiesta arrivata");
            DataManager mm = new MongoDataManager();
            mm.setPassword(Cdf.getCDF(cdf),password);
            return Response.status(Response.Status.OK)
                    .entity("Password cambiata correttamente")
                    .build();
        }catch (DipendenteNotFoundException | AssertionError e){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

}