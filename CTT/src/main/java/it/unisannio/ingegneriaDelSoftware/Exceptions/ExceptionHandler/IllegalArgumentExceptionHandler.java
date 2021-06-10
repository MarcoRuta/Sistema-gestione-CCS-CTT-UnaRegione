package it.unisannio.ingegneriaDelSoftware.Exceptions.ExceptionHandler;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**Nel momento in cui si verificano degli {@link IllegalArgumentException} significa che sono stati passati dei parametri errati ad una funzione

/**Un handler che si occupa di elaborare una risposta nel momento in cui viene sollevata dal server
 * una {@link IllegalArgumentException}*/

public class IllegalArgumentExceptionHandler implements ExceptionMapper<IllegalArgumentException> {
    @Override
    public Response toResponse(IllegalArgumentException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(exception.getMessage())
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}