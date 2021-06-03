package it.unisannio.ingegneriaDelSoftware.ClientRest;

import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;
import it.unisannio.ingegneriaDelSoftware.Util.Settings;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.net.InetAddress;


/**Client REST che viene utilizzato per verificare lo stato di connessione con il CCS*/
public class ConnectionVerifier {

	/**Verifica lo stato di connessione del CTT con il CCS, inviando un PING
	 * @return true se il CCS è collegato correttamente, false se non è stato possibile contattare il CCS
	 */
    public static boolean isCCSOnline(){
    	try {
			CttDataBaseRestApplication.logger.error("Controllo che il CCS sia online");
			Client client = ClientBuilder.newClient();
			WebTarget verificoConnessione = client
					.target("http://"+Settings.ccsIp+":"+Settings.ccsIpPort+"/rest/connessione");
			verificoConnessione.request().get();
    		return true;
    	}catch(ProcessingException p) {
    		CttDataBaseRestApplication.logger.error("L'endPoint rest CCS è OFFLINE");
    		return false;
    	}
    }
}

