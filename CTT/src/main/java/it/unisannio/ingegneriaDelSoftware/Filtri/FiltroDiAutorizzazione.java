package it.unisannio.ingegneriaDelSoftware.Filtri;

import javax.annotation.Priority;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Filtro di autorizzazione, è eseguito per ogni class resource o method resource.
 * Ha una priorità Priorities.AUTHORIZATION che viene eseguita dopo Priorities.AUTHENTICATION.
 *
 * Questo filtro preleva la resource class injected nel ResourceInfo object e controlla le annotazioni che la decorano.
 * Se è presente @PermitAll non attua nessun filtro
 * Se è presente @RolesAllowed("unRuolo") controlla il ruolo dell'user che ha effettuato il login, se non è tra i roles allowed
 * allora rigetta la richiesta
 */
@Provider
@Priority(Priorities.AUTHORIZATION)
public class FiltroDiAutorizzazione implements ContainerRequestFilter {

    /**Instead of injecting values directly into field the value can be injected into the setter method which will initialize the field.
     * This injection can be used only with @Context annotation.
     * resourceInfo contiente i dati relativi alla resource che è stata richiesta attraverso la richiesta intercettata*/
    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // @DenyAll can't be attached to classes

        // @RolesAllowed on the class takes precedence over @PermitAll on the class
        RolesAllowed rolesAllowed =
                resourceInfo.getResourceClass().getAnnotation(RolesAllowed.class);
        if (rolesAllowed != null) {
            performAuthorization(rolesAllowed.value(), requestContext);
        }

        // @PermitAll on the class
        if (resourceInfo.getResourceClass().isAnnotationPresent(PermitAll.class)) {
            // Do nothing
            return;
        }

    }

    /**Perform authorization based on roles.
     * @param rolesAllowed Lista di ruoli che hanno il permesso
     * @param requestContext  provides request-specific information for the filter,
     * such as request URI, message headers, message entity or request-scoped properties.
     * cookies contiene tutti i cookie della richiesta.
     */
    private void performAuthorization(String[] rolesAllowed,
                                      ContainerRequestContext requestContext) {
        if (rolesAllowed.length == 0 || !isAuthenticated(requestContext)) {
            refuseRequest(requestContext);
        }
        for (String role : rolesAllowed)
            if (requestContext.getSecurityContext().isUserInRole(role))
                return;
    //nego l'accesso perchè non è il ruolo adatto
        refuseRequest(requestContext);
    }

    /**Controlla se l'utente è autenticato
     * @param requestContext
     * @return true se l'utente è autenticato, altrimenti false
     */
    private boolean isAuthenticated(final ContainerRequestContext requestContext) {
        return requestContext.getSecurityContext().getUserPrincipal() != null;
    }

    /**Rifiuta la richiesta per accedere alla risorsa.
     * @param requestContext
     */
    private void refuseRequest(ContainerRequestContext requestContext) {
        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                .entity("Non hai i permessi per accedere a queste risorse").build());
        return;
    }
}