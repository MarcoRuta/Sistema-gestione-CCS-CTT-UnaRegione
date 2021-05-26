package it.unisannio.ingegneriaDelSoftware.Exceptions.ExceptionHandler;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.net.MalformedURLException;


/**Nel momento in cui si verificano degli {@link java.net.MalformedURLException} significa che Il server internamente
 * ha fallito la generazione di un URL in risposta ad un POST o in seguito ad altre elaborazioni*/

/**Un handler che si occupa di elaborare una risposta nel momento in cui viene sollevata dal server
 * una {@link MalformedURLException}*/

@Provider
public class MarlFormedURLExceptionHandler implements ExceptionMapper<MalformedURLException> {
    @Override
    public Response toResponse(MalformedURLException exception) {
        return  Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(exception)
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}
