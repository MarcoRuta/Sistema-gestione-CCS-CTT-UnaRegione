package it.unisannio.ingegneriaDelSoftware.Exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class SaccaNotFoundException extends WebApplicationException {

    
    public SaccaNotFoundException (String message) {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity(message).type(MediaType.TEXT_PLAIN).build());
    }
}