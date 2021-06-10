package it.unisannio.ingegneriaDelSoftware.Exceptions.ExceptionHandler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**Nel momento in cui si verificano degli {@link InterruptedException} significa che un thread si trova in uno stato non aspettato (es. wait/sleep)

/**Un handler che si occupa di elaborare una risposta nel momento in cui viene sollevata dal server
 * una {@link InterruptedException}*/
public class InterruptedExceptionHandler implements ExceptionMapper<InterruptedException> {
    @Override
    public Response toResponse(InterruptedException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Non è stato possibile ripetere la richiesta per allinearsi con lo stato del CCS." +
                "\nContattare il CCS per risolvere il problema").build();
    }
}