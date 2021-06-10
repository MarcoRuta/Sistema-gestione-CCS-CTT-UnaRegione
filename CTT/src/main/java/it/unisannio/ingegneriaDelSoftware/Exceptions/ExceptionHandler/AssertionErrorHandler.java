package it.unisannio.ingegneriaDelSoftware.Exceptions.ExceptionHandler;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**Nel momento in cui si verificano degli {@link AssertionError} significa che i parametri passati
 * non sono corretti*/

/**Un handler che si occupa di elaborare una risposta nel momento in cui viene sollevata dal server
 * una {@link AssertionError} exception*/
@Provider
public class AssertionErrorHandler implements ExceptionMapper<AssertionError> {

    @Override
    public Response toResponse(AssertionError exception) {
       return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(exception.getMessage())
                .type(MediaType.TEXT_PLAIN)
                .build();
    }

}