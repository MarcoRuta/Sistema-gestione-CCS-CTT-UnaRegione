package it.unisannio.ingegneriaDelSoftware.Functional;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.CTTName;
import it.unisannio.ingegneriaDelSoftware.Util.Settings;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**Client REST che viene utilizzato per verificare lo stato di connessione con il CCS*/
public class ConnectionVerifier {

	/**Verifica lo stato di connessione del CTT con il CCS, inviando un PING
	 * @return true se il CCS è collegato correttamente, false se non è stato possibile contattare il CCS
	 */
    public static Map<CTTName,String> isCTTOnline(){
    	
    	Map<CTTName,String> cttOnline = new HashMap<CTTName,String>();
    	
    	for(CTTName ctt : Settings.ip.keySet()) {
    		try{	

				Socket socket = new Socket();
				socket.connect(new InetSocketAddress(Settings.ip.get(ctt), Integer.parseInt(Settings.PORTA)), 3000);
				cttOnline.put(ctt, Settings.ip.get(ctt));;
				socket.close();	
		    
    		}catch (IOException e) {
    			//do nothing
    		}
    	}
	return cttOnline;
    }
}