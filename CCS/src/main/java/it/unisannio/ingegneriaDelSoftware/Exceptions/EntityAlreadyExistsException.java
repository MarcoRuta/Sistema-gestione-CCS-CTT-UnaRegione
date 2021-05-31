package it.unisannio.ingegneriaDelSoftware.Exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class EntityAlreadyExistsException extends Throwable {

    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
