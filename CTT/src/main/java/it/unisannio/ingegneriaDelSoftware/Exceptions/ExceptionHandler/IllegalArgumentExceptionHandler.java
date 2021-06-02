package it.unisannio.ingegneriaDelSoftware.Exceptions.ExceptionHandler;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class IllegalArgumentExceptionHandler implements ExceptionMapper<IllegalArgumentException> {
	
    @Override
    public Response toResponse(IllegalArgumentException exception) {
        return   Response
                .status(Response.Status.BAD_REQUEST)
                .entity(exception.getMessage())
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}