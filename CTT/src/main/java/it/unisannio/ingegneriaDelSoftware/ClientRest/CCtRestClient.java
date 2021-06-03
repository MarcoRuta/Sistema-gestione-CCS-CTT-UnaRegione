package it.unisannio.ingegneriaDelSoftware.ClientRest;

import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;
import it.unisannio.ingegneriaDelSoftware.Util.Settings;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import java.util.List;

/**Client REST che viene utilizzato dal CTT per ritirare l'alert di una sacca in scadenza*/
public class CCtRestClient {

	/**Contatta il CCS per avvisarlo di aver consumato il locale una sacca per cui era stato inviato un alert
	 * @param sacca, la Sacca per la quale si vuole ritirare l'alert
	 */
    public static void  notifyEvasioneSaccaToCCS(Sacca sacca){
        Client client = ClientBuilder.newClient();
        WebTarget evasioneSacca = client
                .target("http://"+Settings.ccsIp+":"+Settings.ccsIpPort+"/rest/CCS/ritiroAlertCTT")
                .path(sacca.getSeriale().getSeriale());
        evasioneSacca.request().delete();
        CttDataBaseRestApplication.logger.info("Avviso il CCS che ho consumato una delle Sacche in Scadenza");
    }


    public static void  notifySaccaInScadenzaToCCS(List<Sacca> saccheInScadenza){
        Client client = ClientBuilder.newClient();
        WebTarget gestioneSaccheInscadenza = client
                .target("http://"+Settings.ccsIp+":"+Settings.ccsIpPort+"/rest/CCS/saccheInScadenza");
        gestioneSaccheInscadenza.request().post(Entity.json(saccheInScadenza));
        CttDataBaseRestApplication.logger.info("Ho inviato un alert al CCS con la lista delle sacche in scadenza");
    }
}
