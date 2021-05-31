package WebSocketConfig;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import com.google.gson.Gson;

import it.unisannio.ingegneriaDelSoftware.Classes.NotificaEvasione;



public class NotificaEvasioneEncoder implements Encoder.Text<NotificaEvasione>{
	
	  private static Gson gson = new Gson();

	  public String encode(NotificaEvasione notifica) throws EncodeException {
    
		System.out.println("**ENCODING IN CORSO**");
//	    String jsonString = null;
//		try {  
//	      jsonString =  new ObjectMapper().writeValueAsString(notifica);
//		} catch (Exception e) {}
//		return jsonString;
	    
		String json = gson.toJson(notifica, NotificaEvasione.class);
		System.out.println(json);
        return json;
	  }

	  public void init(EndpointConfig ec) {
	    System.out.println("MessageEncoder - init method called");
	  }

	  public void destroy() {
	    System.out.println("MessageEncoder - destroy method called");
	  }
}