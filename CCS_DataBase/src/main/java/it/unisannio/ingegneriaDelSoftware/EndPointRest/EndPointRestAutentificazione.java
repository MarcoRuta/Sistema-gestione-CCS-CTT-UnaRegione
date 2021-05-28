package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.PasswordGenerator;
import it.unisannio.ingegneriaDelSoftware.Classes.Token;
import it.unisannio.ingegneriaDelSoftware.Classes.User;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.DipendenteNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.InvalidChangePasswordException;
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
    
    
    /**Modifica la password di un utente
     * @param password la nuova password
     * @param header il token di autentificazione
     */
    @PUT
    @Path("/cambiopassword")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response cambioPassword(@HeaderParam(HttpHeaders.AUTHORIZATION) String header, String password) throws AssertionError, DipendenteNotFoundException{
        String requestToken = header.substring("Basic ".length());
        Dipendente dip = Token.getDipendenteByToken(requestToken);
        mm.setPassword(dip.getCdf(), password);
        return Response.status(Response.Status.OK)
                .type(MediaType.TEXT_PLAIN)
                .entity("Password cambiata correttamente")
                .build();

    }



    /**Recupera la password di un Utente
     * @param username L'username dell'Utente che ha perso la password
     * @param cdf cdf dell'Utente che vuole recuperare la password
     */
    @PUT
    @Path("/recuperoPassword/{cdf}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response recuperoPassword(@PathParam("cdf")String cdf, String username) throws AssertionError, DipendenteNotFoundException{
        Dipendente dip = MongoDataManager.getInstance().getDipendente(Cdf.getCDF(cdf));
        String password = PasswordGenerator.getPassword();
        if (dip.getUsername().equals(username)) {
            mm.setPassword(dip.getCdf(), password);
            return Response.status(Response.Status.OK)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("La tua nuova password è: "+password)
                    .build();
        }
        throw  new InvalidChangePasswordException("Username e Codice Fiscale non appartengono alla stessa persona");

    }
}