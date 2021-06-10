package it.unisannio.ingegneriaDelSoftware.Exceptions.ExceptionHandler;

import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**Nel momento in cui si verificano degli {@link EntityAlreadyExistsException} significa che non è stato possibile creare un entità dato che già esisteva

/**Un handler che si occupa di elaborare una risposta nel momento in cui viene sollevata dal server
 * una {@link EntityAlreadyExistsException} exception*/

public class EntityAlreadyExistsExceptionHandler implements ExceptionMapper<EntityAlreadyExistsException> {
    @Override
    public Response toResponse(EntityAlreadyExistsException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(exception.getMessage())
                .build();
    }
}