package it.unisannio.ingegneriaDelSoftware.Exceptions.ExceptionHandler;


import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**Nel momento in cui si verificano degli {@link EntityNotFoundException} significa che non è stato possibile trovare un'entità all'interno del database

/**Un handler che si occupa di elaborare una risposta nel momento in cui viene sollevata dal server
 * una {@link EntityNotFoundException}*/

public class EntityNotFoundExceptionHandler implements ExceptionMapper<EntityNotFoundException>{
    @Override
    public Response toResponse(EntityNotFoundException exception) {
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(exception.getMessage())
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}