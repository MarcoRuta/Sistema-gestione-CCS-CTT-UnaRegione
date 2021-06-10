package it.unisannio.ingegneriaDelSoftware.Exceptions.ExceptionHandler;

import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**Nel momento in cui si verificano degli {@link EntityAlreadyExistsException} significa che l'entità già è presente nel database

 /**Un handler che si occupa di elaborare una risposta nel momento in cui viene sollevata dal server
 * una {@link EntityAlreadyExistsException}*/

public class EntityAlreadyExistsExceptionHandler implements ExceptionMapper<EntityAlreadyExistsException> {
	
    @Override
    public Response toResponse(EntityAlreadyExistsException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity(exception.getMessage())
	        .type(MediaType.TEXT_PLAIN)
	        .build();
    }
}