package WebSocketConfig;
import java.util.ArrayList;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.GestioneScadenze.NotificaSaccaInScadenza;



public class NotificaSaccaInScadenzaDecoder implements Decoder.Text< ArrayList<NotificaSaccaInScadenza>> {
	
	
	public ArrayList<NotificaSaccaInScadenza> decode(String jsonMessage) throws DecodeException {

		System.out.println("**DECODING IN CORSO**");
	    ArrayList<NotificaSaccaInScadenza> notifiche = new ArrayList<>();
		try {
			notifiche = new ObjectMapper().readValue(jsonMessage,ArrayList.class);
			return notifiche;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
        
        
	  }

	  public boolean willDecode(String jsonMessage) {
	    try {
	      // Check if incoming message is valid JSON
	      new ObjectMapper().readValue(new String(jsonMessage), Sacca[].class);
	      return true;
	    } catch (Exception e) {
	      return false;
	    }
	  }

	  public void init(EndpointConfig ec) {
	    System.out.println("MessageDecoder -init method called");
	  }

	  public void destroy() {
	    System.out.println("MessageDecoder - destroy method called");
	  }

}