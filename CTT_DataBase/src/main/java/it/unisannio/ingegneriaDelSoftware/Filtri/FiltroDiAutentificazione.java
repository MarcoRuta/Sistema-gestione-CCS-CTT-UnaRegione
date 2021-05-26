package it.unisannio.ingegneriaDelSoftware.Filtri;

import it.unisannio.ingegneriaDelSoftware.Annotazioni.Secured;
import it.unisannio.ingegneriaDelSoftware.Classes.Token;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.DipendenteNotFoundException;
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


/**
 * Filtro di autentificazione, è eseguito per ogni class resource o method resource in cui è presente
 * l'anntoazione @Secured. Esso è eseguito dopo il matching della risorsa da parte dall'api di jersey.
 * Ha una priorita Priorities.AUTHENTICATION che viene eseguita prima di qualsiasi altra Priorities
 * in quanto ha un valore intero più basso delle altre priorità
 * Questo filtro preleva i cookie dalla richieste del browser e verifica che ci sia il cookie con l'accesso token
 * se esso non è presente il filtro impedisce alla richiesta di arrivare alla risorsa prestabilita
 * nel momento in cui il token è presente ed è valido il filtro sovrascrive il Security context con i dati dell'user
 * associato al token*/
@Provider
@Secured
@Priority(Priorities.AUTHENTICATION)
public class FiltroDiAutentificazione implements ContainerRequestFilter {

    /**
     * Instead of injecting values directly into field the value can be injected into the setter method which will initialize the field.
     * This injection can be used only with @Context annotation.
     * resourceInfo contiente i dati relativi alla resource che è stata richiesta attraverso la richiesta intercettata*/
    @Context
    private ResourceInfo resourceInfo;


    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
       String header = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if(header == null || !tokenValid(header)) {
            this.refuseRequest(requestContext, "Effettua Prima il login");
            return;
        }
        String token = header.substring(SecurityContext.BASIC_AUTH.length()).trim();
        if(!Token.containsToken(token)){
            this.refuseRequest(requestContext,"Token non valido effettua nuovamente il login");
            return;
        }

        //recupero username e password usando il token
        try {
            String usernamePassword = Token.getDipendenteByToken(token);
            StringTokenizer aTokenizer = new StringTokenizer(usernamePassword);
            String username = aTokenizer.nextToken(":");
            String password = aTokenizer.nextToken();
            String ruolo = MongoDataManager.getDipendente(username,password).getRuolo().toString();

            //sovrascrivo il securityContext
            /**The SecurityContext is a class of Spring Security.
             * The SecurityContext is used to store the details of the currently authenticated user,
             * also known as a principle*/
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
                    return SecurityContext.BASIC_AUTH;
                }
            });

        } catch (DipendenteNotFoundException e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Dipendente non registrato nel DB").build());
            return;
        }


    }

    private boolean tokenValid(String header) {
        if (!header.toUpperCase().startsWith(SecurityContext.BASIC_AUTH+" "))
            return false;
       return true;
    }

    private void refuseRequest(ContainerRequestContext requestContext, String message) {
        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                .entity(message).build());
    }
}
