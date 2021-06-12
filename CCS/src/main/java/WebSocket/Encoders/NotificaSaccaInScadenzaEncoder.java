package WebSocket.Encoders;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisannio.ingegneriaDelSoftware.CcsRestApplication;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche.NotificaSaccaInScadenza;

public class NotificaSaccaInScadenzaEncoder implements Encoder.Text<ArrayList<NotificaSaccaInScadenza>>{
	
	/**Restituisce l'oggetto json correttamente serializzato, dato un ArrayList<SaccaInScadenza>
	 * @param ArrayList<SaccaInScadenza> lista delle sacche in scadenza
	 * @return Il json corrispondente alla lista delle sacche in scadenza
	 */
	public String encode(ArrayList<NotificaSaccaInScadenza> notifiche) throws EncodeException {
		try {
		String json = new ObjectMapper().writeValueAsString(notifiche);
		CcsRestApplication.logger.info("Ho serializzato correttamente la lista di notificheSaccheInScadenza: "+notifiche);
        return json;
		}catch (JsonProcessingException e) {
		e.printStackTrace();
		return null;
		}
	}

	/**Inizializzatore della WebSocket
	 */
	public void init(EndpointConfig ec) {
	}

	/**Chiusura della WebSocket
	 */
	public void destroy() {
	}
}