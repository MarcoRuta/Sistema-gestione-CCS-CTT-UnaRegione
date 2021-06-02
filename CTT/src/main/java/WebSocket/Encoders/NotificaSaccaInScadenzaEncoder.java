package WebSocket.Encoders;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaSaccaInScadenza;
import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;

public class NotificaSaccaInScadenzaEncoder implements Encoder.Text<ArrayList<NotificaSaccaInScadenza>>{
	
	/**Restituisce l'oggetto json correttamente serializzato, dato un ArrayList<NotificaSaccaInScadenza>
	 * @param ArrayList<NotificaSaccaInScadenza> lista delle notifiche delle sacche in scadenza
	 * @return Il json corrispondente alla lista delle notifiche delle sacche in scadenza
	 */
	public String encode(ArrayList<NotificaSaccaInScadenza> notifiche) throws EncodeException {
		try {
			String json = new ObjectMapper().writeValueAsString(notifiche);
			CttDataBaseRestApplication.logger.info("Ho serializzato correttamente la lista delle notifiche sacche in scadenza: "+notifiche);
			return json;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**Inizializzatore della WebSocket*/
	public void init(EndpointConfig ec) {}
	
	
	/**Chiusura della WebSocket*/
	public void destroy() {}
}