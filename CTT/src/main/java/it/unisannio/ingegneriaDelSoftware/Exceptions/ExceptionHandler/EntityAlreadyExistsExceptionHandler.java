package it.unisannio.ingegneriaDelSoftware.Exceptions.ExceptionHandler;

import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class EntityAlreadyExistsExceptionHandler implements ExceptionMapper<EntityAlreadyExistsException> {
    @Override
    public Response toResponse(EntityAlreadyExistsException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(exception.getMessage())
                .build();
    }
}
