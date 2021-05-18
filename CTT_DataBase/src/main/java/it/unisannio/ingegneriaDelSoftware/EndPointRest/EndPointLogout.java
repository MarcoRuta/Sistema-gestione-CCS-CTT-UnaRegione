package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.Token;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.DipendenteNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.TokenNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.DataManager;

import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/sessione")
public class EndPointLogout {

    /**
     * Metodo che effettua il logout, esso elimina il token dell'utente dal server cosi che esso non sia piu autenticato
     *
     * @param token da rimuovere per effetuare il logout
     */
    @DELETE
    @Path("/logout")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response logOut(@CookieParam("access_token") Cookie token) {
        try {
//            DataManager mm = new MongoDataManager();
            Token.removeToken(token.getValue());
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