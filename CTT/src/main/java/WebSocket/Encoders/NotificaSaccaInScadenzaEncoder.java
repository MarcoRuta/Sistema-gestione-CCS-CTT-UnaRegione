package WebSocket.Encoders;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche.NotificaSaccaInScadenza;
import it.unisannio.ingegneriaDelSoftware.CttRestApplication;


public class NotificaSaccaInScadenzaEncoder implements Encoder.Text<ArrayList<NotificaSaccaInScadenza>>{
	
	  @Override
	  public String encode(ArrayList<NotificaSaccaInScadenza> notifiche) throws EncodeException {
		try {
			String json = new ObjectMapper().writeValueAsString(notifiche);
			CttRestApplication.logger.info("Ho serializzato correttamente la lista delle notifiche sacche in scadenza: "+notifiche);
	        return json;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
		
	  }
	  @Override
	  public void init(EndpointConfig ec) {

	  }
	  @Override
	  public void destroy() {

	  }
}