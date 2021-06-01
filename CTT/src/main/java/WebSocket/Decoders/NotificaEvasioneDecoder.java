package WebSocket.Decoders;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaEvasione;
import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;


public class NotificaEvasioneDecoder implements Decoder.Text<NotificaEvasione> {
	
	private static Gson gson = new Gson();
	
	public NotificaEvasione decode(String jsonMessage) throws DecodeException {

		NotificaEvasione notifica = gson.fromJson(jsonMessage, NotificaEvasione.class);
		CttDataBaseRestApplication.logger.info("NotificaEvasione decodificata correttamente: "+notifica);
        return notifica;
        
	  }

	  public boolean willDecode(String jsonMessage) {
	    try {
	      // Check if incoming message is valid JSON
	      new ObjectMapper().readValue(new String(jsonMessage), NotificaEvasione.class);
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