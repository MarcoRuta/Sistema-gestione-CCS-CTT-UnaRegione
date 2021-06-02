package it.unisannio.ingegneriaDelSoftware.ClientRest;

import it.unisannio.ingegneriaDelSoftware.Util.Constants;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class ConnectionVerifier {

    public static boolean isCCSOnline(){
        try {
            Client client = ClientBuilder.newClient();
            WebTarget connectionStatus = client
                    .target(Constants.CCSIP+"/rest/connessione/statoConnessione");
            connectionStatus.request().get();
            return true;
        }catch (ProcessingException e) {
            return false;
        }
    }
}
