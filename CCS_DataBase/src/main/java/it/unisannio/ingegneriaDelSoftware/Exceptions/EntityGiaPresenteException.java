package it.unisannio.ingegneriaDelSoftware.Exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class EntityGiaPresenteException extends WebApplicationException {

	private static final long serialVersionUID = 1L;

	public EntityGiaPresenteException(String message) {
        super(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(message)
                .type(MediaType.TEXT_PLAIN)
                .build());
    }
}
