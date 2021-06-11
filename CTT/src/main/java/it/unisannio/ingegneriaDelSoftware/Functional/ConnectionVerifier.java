package it.unisannio.ingegneriaDelSoftware.Functional;

import it.unisannio.ingegneriaDelSoftware.CttRestApplication;
import it.unisannio.ingegneriaDelSoftware.Util.Settings;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;


/**Client REST che viene utilizzato per verificare lo stato di connessione con il CCS*/
public class ConnectionVerifier {

	/**Verifica lo stato di connessione del CTT con il CCS, inviando un PING
	 * @return true se il CCS è collegato correttamente, false se non è stato possibile contattare il CCS
	 */
    public static boolean isCCSOnline(){
		try{
			Socket socket = new Socket();
			socket.connect(new InetSocketAddress(Settings.ccsIp, Integer.parseInt(Settings.ccsIpPort)), 5000);
			CttRestApplication.logger.info("EndPointRest CCS è online");
			return true;
		} catch (IOException e) {
			CttRestApplication.logger.info("EndPointRest CCS è offline");
			return false; // Either timeout or unreachable or failed DNS lookup.
		}
    }


    
}