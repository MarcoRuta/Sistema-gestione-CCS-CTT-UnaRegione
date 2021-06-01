package it.unisannio.ingegneriaDelSoftware.Exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class InvalidChangePasswordException extends WebApplicationException {
    public InvalidChangePasswordException(String message) {
        super(Response.status(Response.Status.FORBIDDEN)
                .entity(message).type(MediaType.TEXT_PLAIN).build());
    }
}
