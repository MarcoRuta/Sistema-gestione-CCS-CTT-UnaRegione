package it.unisannio.ingegneriaDelSoftware.ClientRest;

import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class CCtRestClient {

    public static void  notifyEvasioneSaccaToCCS(Sacca sacca){
        Client client = ClientBuilder.newClient();
        WebTarget evasioneSacca = client
                .target(Constants.CCSIP+"/rest/CCS/ritiroAlertCTT/")
                .path(sacca.getSeriale().getSeriale());
        evasioneSacca.request().delete();
        CttDataBaseRestApplication.logger.info("Avviso il CCS che ho consumato una delle Sacche in Scadenza");
    }
}
