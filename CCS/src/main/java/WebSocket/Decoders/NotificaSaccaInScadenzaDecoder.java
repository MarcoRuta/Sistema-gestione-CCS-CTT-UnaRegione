package WebSocket.Decoders;
import java.util.ArrayList;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisannio.ingegneriaDelSoftware.CcsDataBaseRestApplication;
import it.unisannio.ingegneriaDelSoftware.Classes.Beans.SaccaBean;
import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaSaccaInScadenza;



public class NotificaSaccaInScadenzaDecoder implements Decoder.Text< ArrayList<NotificaSaccaInScadenza>> {
	
	
	public ArrayList<NotificaSaccaInScadenza> decode(String jsonMessage) throws DecodeException {
		try {
			ArrayList<NotificaSaccaInScadenza> notifiche = new ArrayList<>();
			notifiche = new ObjectMapper().readValue(jsonMessage,ArrayList.class);
			CcsDataBaseRestApplication.logger.info("Ho deserializzato correttamente la lista di notifiche sacche in scadenza: "+notifiche);
			return notifiche;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
        
        
	  }

	  public boolean willDecode(String jsonMessage) {
	    try {
	      new ObjectMapper().readValue(new String(jsonMessage), NotificaSaccaInScadenza[].class);
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