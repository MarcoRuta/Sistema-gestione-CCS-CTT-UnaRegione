package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.Token;
import it.unisannio.ingegneriaDelSoftware.Classes.User;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.DipendenteNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.TokenNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.EndPointAutenticazione;

import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/autentificazione")
public class EndPointRestAutentificazione implements EndPointAutenticazione {
	private MongoDataManager mm = MongoDataManager.getInstance();
	
	
	/**Fa accedere al sistema un Dipedente identificato con Username e Password
     * @return Response
	 * @throws DipendenteNotFoundException 
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("username") String username,
                          @FormParam("password") String password) {
    	try{
    		Dipendente unDipendente = mm.getDipendente(username, password);
    		String token =  Token.getToken(username + ":" + password).getValue();
    		User aUser = new User(token,unDipendente.getRuolo().toString(),unDipendente.getNome(), unDipendente.getCognome());
    		return Response.status(Response.Status.OK)
    		        .entity(aUser)
    		        .build();
    	}catch (DipendenteNotFoundException e) {
    		return Response.status(Response.Status.FORBIDDEN)
    		        .entity(e.getMessage())
    		        .build();
    	}	
    }



    /**
     * Effettua il logout di un Dipendente, eliminando il token dell'utente dal server così che esso non sia più autenticato
     * @param header Intestazione che contiene il token da rimuovere per effettuare il logout
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
    
    
    /**Modifica la password di un Dipendente identificato tramite il suo Codice fiscale
     * @param cdf Il Codice fiscale del Dipendente
     * @param password La nuova password
     */
    @PUT
    @Path("/cambiopassword/{cdf}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response cambioPassword(@PathParam("cdf")String cdf, String password){
        try{
            System.out.println("richiesta arrivata");
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