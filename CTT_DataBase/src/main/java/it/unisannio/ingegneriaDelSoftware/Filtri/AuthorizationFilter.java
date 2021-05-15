package it.unisannio.ingegneriaDelSoftware.Filtri;

import it.unisannio.ingegneriaDelSoftware.Annotazioni.Secured;

import javax.annotation.Priority;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

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

    /**
     * Perform authorization based on roles.
     *
     * @param rolesAllowed
     * @param requestContext
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

    /**
     * Check if the user is authenticated.
     *
     * @param requestContext
     * @return
     */
    private boolean isAuthenticated(final ContainerRequestContext requestContext) {
        return requestContext.getSecurityContext().getUserPrincipal() != null;
    }

    /**
     * Refuse the request.
     * @param requestContext
     */
    private void refuseRequest(ContainerRequestContext requestContext) {
        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                .entity("You cannot access this resource").build());
        return;
    }
}