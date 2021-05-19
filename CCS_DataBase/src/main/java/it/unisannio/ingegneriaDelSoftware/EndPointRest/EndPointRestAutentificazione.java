package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.Token;
import it.unisannio.ingegneriaDelSoftware.Classes.User;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.DipendenteNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.DataManager;
import it.unisannio.ingegneriaDelSoftware.Interfaces.EndPointAutenticazione;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class EndPointRestAutentificazione implements EndPointAutenticazione {

	/**
     * Login è l'operazione con la quale un generico dipendente del ctt accede al sistema
     *
     * @return il ruolo con il quale si è registrati
     * 
     * 
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



	@Override
	public Response logOut(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}
}