package WebSocket.Encoders;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaEvasione;
import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;

import java.util.ArrayList;

public class NotificaEvasioneEncoder implements Encoder.Text<ArrayList<NotificaEvasione>>{

	  public String encode(ArrayList<NotificaEvasione> notifiche) throws EncodeException {

		  try {
			  String json = new ObjectMapper().writeValueAsString(notifiche);
			  CttDataBaseRestApplication.logger.info("ho serializzato correttamente le notificheEvasione: "+json);
			  return json;
		  } catch (JsonProcessingException e) {
			  CttDataBaseRestApplication.logger.error("Problemi nella serializzazione della lista di notifiche evasione: "+notifiche);

		  }
		return null;
	  }

	  public void init(EndpointConfig ec) {

	  }

	  public void destroy() {

	  }
}