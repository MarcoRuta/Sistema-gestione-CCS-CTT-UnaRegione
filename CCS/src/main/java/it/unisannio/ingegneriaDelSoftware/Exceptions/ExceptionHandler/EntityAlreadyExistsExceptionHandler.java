package it.unisannio.ingegneriaDelSoftware.Exceptions.ExceptionHandler;

import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class EntityAlreadyExistsExceptionHandler implements ExceptionMapper<EntityAlreadyExistsException> {
	
    @Override
    public Response toResponse(EntityAlreadyExistsException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity(exception.getMessage())
	        .type(MediaType.TEXT_PLAIN)
	        .build();
    }
}