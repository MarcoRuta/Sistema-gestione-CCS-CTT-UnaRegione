package WebSocketConfig;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import it.unisannio.ingegneriaDelSoftware.Classes.NotificaEvasione;



public class NotificaEvasioneDecoder implements Decoder.Text<NotificaEvasione> {
	
	private static Gson gson = new Gson();
	
	public NotificaEvasione decode(String jsonMessage) throws DecodeException {

		System.out.println("**DECODING IN CORSO**");
//		NotificaEvasione notifica = null; 
//		try {
//		   notifica = new ObjectMapper().readValue(new String(jsonMessage), NotificaEvasione.class);
//		   
//		} catch (Exception e) {System.err.println(e); }   
//		return notifica;
//	    
		NotificaEvasione notifica = gson.fromJson(jsonMessage, NotificaEvasione.class);
		System.out.println(notifica);
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
	    System.out.println("MessageDecoder -init method called");
	  }

	  public void destroy() {
	    System.out.println("MessageDecoder - destroy method called");
	  }

}