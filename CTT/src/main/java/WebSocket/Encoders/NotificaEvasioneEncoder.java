package WebSocket.Encoders;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import com.google.gson.Gson;

import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaEvasione;
import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;


public class NotificaEvasioneEncoder implements Encoder.Text<NotificaEvasione>{
	
	  private static Gson gson = new Gson();

	  public String encode(NotificaEvasione notifica) throws EncodeException {

		String json = gson.toJson(notifica, NotificaEvasione.class);
		CttDataBaseRestApplication.logger.info("ho serializzato correttamente la notificaEvasione: "+notifica);
        return json;
	  }

	  public void init(EndpointConfig ec) {

	  }

	  public void destroy() {

	  }
}