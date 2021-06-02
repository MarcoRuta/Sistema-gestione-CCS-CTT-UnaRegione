package it.unisannio.ingegneriaDelSoftware.Exceptions.ExceptionHandler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InterruptedExceptionHandler implements ExceptionMapper<InterruptedException> {
	
    @Override
    public Response toResponse(InterruptedException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Non è stato possibile ripetere la richiesta per allinearsi con lo stato del CCS." +
                "\nContattare il CCS per risolvere il problema").build();
    }
}