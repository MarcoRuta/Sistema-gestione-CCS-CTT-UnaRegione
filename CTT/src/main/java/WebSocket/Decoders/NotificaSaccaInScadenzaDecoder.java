package WebSocket.Decoders;
import java.util.ArrayList;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche.NotificaSaccaInScadenza;
import it.unisannio.ingegneriaDelSoftware.CttRestApplication;


public class NotificaSaccaInScadenzaDecoder implements Decoder.Text< ArrayList<NotificaSaccaInScadenza>> {
	
	
	public ArrayList<NotificaSaccaInScadenza> decode(String jsonMessage) throws DecodeException {

	    ArrayList<NotificaSaccaInScadenza> notifiche = new ArrayList<>();
		try {
			notifiche = new ObjectMapper().readValue(jsonMessage,ArrayList.class);
			CttRestApplication.logger.info("Ho decodificato la lista di notificheSaccheInScadenza correttamente: "+notifiche);
			return notifiche;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
        
        
	  }

	  public boolean willDecode(String jsonMessage) {
	    try {
	      // Check if incoming message is valid JSON
	      new ObjectMapper().readValue(new String(jsonMessage), ArrayList.class);
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