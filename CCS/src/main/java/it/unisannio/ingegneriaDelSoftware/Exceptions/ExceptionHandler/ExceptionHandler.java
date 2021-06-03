package it.unisannio.ingegneriaDelSoftware.Exceptions.ExceptionHandler;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**Nel momento in cui si verificano degli {@link java.lang.Exception}*/

/**Un handler che si occupa di elaborare una risposta nel momento in cui viene sollevata dal server una {@link java.lang.Exception}*/

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {
	
    @Override
    public Response toResponse(Exception exception) {
        return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity(exception.getMessage())
	        .type(MediaType.TEXT_PLAIN)
	        .build();
    }
}
