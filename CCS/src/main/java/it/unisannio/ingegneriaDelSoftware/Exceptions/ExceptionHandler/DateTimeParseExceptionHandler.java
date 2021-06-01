package it.unisannio.ingegneriaDelSoftware.Exceptions.ExceptionHandler;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.time.format.DateTimeParseException;

public class DateTimeParseExceptionHandler implements ExceptionMapper<DateTimeParseException> {
	
    @Override
    public Response toResponse(DateTimeParseException exception) {
        return Response
	        .status(Response.Status.BAD_REQUEST)
	        .entity(exception.getMessage())
	        .type(MediaType.TEXT_PLAIN)
	        .build();
    }
}