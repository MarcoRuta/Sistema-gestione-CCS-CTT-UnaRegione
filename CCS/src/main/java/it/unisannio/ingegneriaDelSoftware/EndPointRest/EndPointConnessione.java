package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/connessione")
public class EndPointConnessione {

    @Path("/statoConnessione")
    @GET
    public void statoConnesione(){

    }
}
