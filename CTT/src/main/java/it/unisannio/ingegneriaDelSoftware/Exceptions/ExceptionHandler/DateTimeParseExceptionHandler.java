package it.unisannio.ingegneriaDelSoftware.Exceptions.ExceptionHandler;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.time.format.DateTimeParseException;

/**Nel momento in cui si verificano degli {@link DateTimeParseException} significa che non è stato possibile convertire una data*/

/**Un handler che si occupa di elaborare una risposta nel momento in cui viene sollevata dal server
 * una {@link DateTimeParseException} exception*/

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