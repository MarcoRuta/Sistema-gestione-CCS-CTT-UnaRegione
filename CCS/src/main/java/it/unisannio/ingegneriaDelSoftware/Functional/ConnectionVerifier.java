package it.unisannio.ingegneriaDelSoftware.Functional;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.CTT;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.CTTName;
import it.unisannio.ingegneriaDelSoftware.Util.Settings;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**Client REST che viene utilizzato per verificare lo stato di connessione con il CCS*/
public class ConnectionVerifier {

	/**Verifica lo stato di connessione del CTT con il CCS, inviando un PING
	 * @return true se il CCS è collegato correttamente, false se non è stato possibile contattare il CCS
	 */
	public static Map<CTTName,String> isCTTOnline(){

		return Settings.ip.keySet()
				.stream()
				.filter(CTT::isOnline)
				.collect(Collectors.toMap( (CTT ctt)->ctt.getDenominazione(), (CTT ctt)->Settings.ip.get(ctt)));
	}
}