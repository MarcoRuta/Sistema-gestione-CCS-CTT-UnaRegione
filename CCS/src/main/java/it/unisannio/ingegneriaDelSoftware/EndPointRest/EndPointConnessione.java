package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/connessione")
public class EndPointConnessione {

    @GET
    public void connectionVerifier(){
        //do nothing
    }
}
