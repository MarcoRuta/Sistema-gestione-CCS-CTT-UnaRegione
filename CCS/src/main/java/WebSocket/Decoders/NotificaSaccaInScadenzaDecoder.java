package WebSocket.Decoders;

import java.util.ArrayList;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisannio.ingegneriaDelSoftware.CcsRestApplication;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Beans.Sacca;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche.NotificaSaccaInScadenza;

public class NotificaSaccaInScadenzaDecoder implements Decoder.Text< ArrayList<NotificaSaccaInScadenza>> {
	
	/**Deserializza la lista di notifiche sacche in scadenza
	 * @param jsonMessage
	 */
	public ArrayList<NotificaSaccaInScadenza> decode(String jsonMessage) throws DecodeException {
		try {
			ArrayList<NotificaSaccaInScadenza> notifiche = new ArrayList<>();
			notifiche = new ObjectMapper().readValue(jsonMessage,ArrayList.class);
			CcsRestApplication.logger.info("Ho deserializzato correttamente la lista di notifiche sacche in scadenza: "+notifiche);
			return notifiche;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	  }

	/**Controlla se è possibile effettuare la decodifica di un json
	 * @param jsonMessage
	 * @return true se è possibile; altrimenti false
	 */
	public boolean willDecode(String jsonMessage) {
	    try {
	      new ObjectMapper().readValue(new String(jsonMessage), Sacca[].class);
	      return true;
	    } catch (Exception e) {
	      return false;
	    }
	  }

	  public void init(EndpointConfig ec) {
	  }

	  public void destroy() {
	  }
}