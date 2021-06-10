package it.unisannio.ingegneriaDelSoftware.Filtri;

import it.unisannio.ingegneriaDelSoftware.Annotazioni.Secured;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Token;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Util.Settings;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;

/**Filtro di autentificazione, è eseguito per ogni class resource o method resource in cui è presente
 * l'annotazione @Secured. Esso è eseguito dopo il matching della risorsa da parte dall'api di jersey.
 * Ha una priorità Priorities.AUTHENTICATION che viene eseguita prima di qualsiasi altra Priorities
 * in quanto ha un valore intero più basso delle altre priorità
 * Questo filtro preleva i cookie dalla richieste del browser e verifica che ci sia il cookie con l'accesso token
 * se esso non è presente il filtro impedisce alla richiesta di arrivare alla risorsa prestabilita
 * nel momento in cui il token è presente ed è valido il filtro sovrascrive il Security context con i dati dell'user
 * associato al token*/
@Provider
@Secured
@Priority(Priorities.AUTHENTICATION)
public class FiltroDiAutentificazione implements ContainerRequestFilter {

    /**Instead of injecting values directly into field the value can be injected into the setter method which will initialize the field.
     * This injection can be used only with @Context annotation.
     * resourceInfo contenente i dati relativi alla resource che è stata richiesta attraverso la richiesta intercettata*/
    @Context
    private ResourceInfo resourceInfo;
    @Context
    private HttpServletRequest servletRequest;


    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        //sono il CCS
        if (this.isCCS(requestContext))
            return;

       String header = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if(header == null || !tokenValid(header)) {
            this.refuseRequest(requestContext, "Effettua prima il login");
            return;
        }
        String token = header.substring(SecurityContext.BASIC_AUTH.length()).trim();
        if(!Token.containsToken(token)){
            this.refuseRequest(requestContext,"Token non valido effettua nuovamente il login");
            return;
        }

        //recupero username e password usando il token
        try {
            Dipendente dip = Token.getDipendenteByToken(token);
            String ruolo = dip.getRuolo().toString();

            //sovrascrivo il securityContext
            /**The SecurityContext is a class of Spring Security.
             * The SecurityContext is used to store the details of the currently authenticated user,
             * also known as a principle*/
            final SecurityContext currentSecurityContext = requestContext.getSecurityContext();
            requestContext.setSecurityContext(new SecurityContext() {

                @Override
                public Principal getUserPrincipal() {
                    return () -> dip.getUsername();
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

        } catch (EntityNotFoundException e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Dipendente non registrato nel DB").build());
            return;
        }
    }

    
    /**Controlla se è il CCS
     * @param requestContext
     * @return true se è il CCS, altrimenti false
     */
    private boolean isCCS(ContainerRequestContext requestContext) {
        String requestIp =this.servletRequest.getRemoteAddr();
        String ccsIp= Settings.ccsIp;
        if(requestIp.equals(ccsIp)){
            //dato che sono il CCS sovrascrivo il security context
            final SecurityContext currentSecurityContext = requestContext.getSecurityContext();
            requestContext.setSecurityContext(new SecurityContext() {

                @Override
                public Principal getUserPrincipal() {
                    return () -> "CCS";
                }

                @Override
                public boolean isUserInRole(String role) {
                    return role.equals(RuoloDipendente.CCS.toString());
                }

                @Override
                public boolean isSecure() {
                    return currentSecurityContext.isSecure();
                }

                @Override
                public String getAuthenticationScheme() {
                    return servletRequest.getAuthType();
                }
            });
            //sono il CCS
            return true;
        }
        //non sono il CCS
        return false;
    }

    
    /**Controlla se il Token ha un formato valido
     *@param header
     *@return true se il Token ha un formato valido, altrimenti false
     */
    private boolean tokenValid(String header) {
        if (!header.toUpperCase().startsWith(SecurityContext.BASIC_AUTH+" "))
            return false;
       return true;
    }

    
    /**Rifiuta la richiesta 
     * @param requestContext
     * @param message
     */
    private void refuseRequest(ContainerRequestContext requestContext, String message) {
        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                .entity(message).build());
    }
}