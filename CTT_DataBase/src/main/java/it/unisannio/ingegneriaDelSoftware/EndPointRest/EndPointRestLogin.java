package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.Token;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Interfaces.DataManager;
import it.unisannio.ingegneriaDelSoftware.Interfaces.EndPointLogin;

import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

@Path("/login")
public class EndPointRestLogin implements EndPointLogin {

    /**
     * Login è l'operazione con la quale un generico dipendente del ctt accede al sistema
     *
     * @return il ruolo con il quale si è registrati
     * esso setta il token di autentificazione nei cookie con cookieName = "access_token
     * il cookie ha una max-Age pari ad un turno lavorativo di 8 ore"
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(@FormParam("username") String username,
                          @FormParam("password") String password) {
        DataManager mm = new MongoDataManager();
        Dipendente unDipendente = mm.getDipendente(username, password);
        if (unDipendente != null) {
            Cookie aCookie = new Cookie("access_token", Token.getToken(username + ":" + password).getValue());
            return Response.status(Response.Status.OK)
                    .entity(unDipendente.getRuolo().toString())
                    .cookie(new NewCookie(aCookie, "token di accesso", 60 * 60 * 8, false))
                    .build();
        } else
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("username o password errati!")
                    .build();
    }

}

