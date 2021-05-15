package it.unisannio.ingegneriaDelSoftware.Filtri;

import it.unisannio.ingegneriaDelSoftware.Annotazioni.Secured;
import it.unisannio.ingegneriaDelSoftware.Classes.Token;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.DipendenteNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.DataManager;


import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;
import java.util.*;


@Provider
@Secured
//So, a request filter with priority defined with @Priority(1000)
//will be executed before another request filter with priority defined as @Priority(2000).
@Priority(Priorities.AUTHENTICATION)
public class FiltroDiAutentificazione implements ContainerRequestFilter {
    private List<String>tokens = new ArrayList<>();

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        DataManager dataManager = new MongoDataManager();
        Map<String, Cookie> cookies = requestContext.getCookies();
        //If no authorization information present; block access
        if(cookies == null || cookies.isEmpty() ) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .entity("You cannot access this resource").build());
            return;
        }
        //controllo se ho la chiave di accesso altrimenti blocco l'accesso
       if(!(cookies.containsKey("access_token") )|| cookies.get("access_token")== null){
           requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                   .entity("You cannot access this resource").build());
           return;
       }

       //controllo se sia un token valido
       if(! (Token.containsToken(cookies.get("access_token").getValue())) ){
           requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                   .entity("Token di accesso non valido, Effettuare nuovamente il login").build());
           return;
       }
       //recupero username e password usando il token
        try {
            String usernamePassword = Token.getDipendenteByToken(cookies.get("access_token").getValue());
            StringTokenizer aTokenizer = new StringTokenizer(usernamePassword);
            String username = aTokenizer.nextToken(":");
            String password = aTokenizer.nextToken();
            String ruolo = dataManager.getDipendente(username,password).getRuolo().toString();

            //savrascrivo il securityContext
            final SecurityContext currentSecurityContext = requestContext.getSecurityContext();
            requestContext.setSecurityContext(new SecurityContext() {

                @Override
                public Principal getUserPrincipal() {
                    return () -> username;
                }

                @Override
                public boolean isUserInRole(String role) {
                    return role.equals(ruolo);
                }

                @Override
                public boolean isSecure() {
                    return currentSecurityContext.isSecure();
                }

                @Override
                public String getAuthenticationScheme() {
                    return "";
                }
            });

        } catch (DipendenteNotFoundException e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Token di accesso non valido").build());
            return;
        }


    }


}
