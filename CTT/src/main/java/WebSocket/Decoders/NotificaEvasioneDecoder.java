package WebSocket.Decoders;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaEvasione;
import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;

import java.util.ArrayList;


public class NotificaEvasioneDecoder implements Decoder.Text<ArrayList<NotificaEvasione>> {
	
	public ArrayList<NotificaEvasione> decode(String jsonMessage) throws DecodeException {

		try {
			ArrayList<NotificaEvasione> notifiche = new ObjectMapper().readValue(jsonMessage, ArrayList.class);
			CttDataBaseRestApplication.logger.info("lista di NotificaEvasione decodificata correttamente: "+notifiche);
			return notifiche;

		} catch (JsonProcessingException e) {
			//do nothing
		}
		return null;
	  }

	
	  public boolean willDecode(String jsonMessage) {
		  try {
			  new ObjectMapper().readValue(jsonMessage, ArrayList.class);
			  return true;
		  } catch (JsonProcessingException e) {
			  CttDataBaseRestApplication.logger.error("Problemi nella codifica della lista di EvasioniSacca");
			  return false;
		  }
	  }

	  public void init(EndpointConfig ec) {
	  }

	  public void destroy() {
	  }

}