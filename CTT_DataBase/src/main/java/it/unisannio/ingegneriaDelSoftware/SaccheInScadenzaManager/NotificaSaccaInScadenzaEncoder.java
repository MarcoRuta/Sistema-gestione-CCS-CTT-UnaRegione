package it.unisannio.ingegneriaDelSoftware.SaccheInScadenzaManager;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;




public class NotificaSaccaInScadenzaEncoder implements Encoder.Text<ArrayList<NotificaSaccaInScadenza>>{
	


	  public String encode(ArrayList<NotificaSaccaInScadenza> notifiche) throws EncodeException {
    
		System.out.println("**ENCODING IN CORSO**");
	    
		String json;
		try {
			json = new ObjectMapper().writeValueAsString(notifiche);
			System.out.println(json);
	        return json;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
		
	  }

	  public void init(EndpointConfig ec) {
	    System.out.println("MessageEncoder - init method called");
	  }

	  public void destroy() {
	    System.out.println("MessageEncoder - destroy method called");
	  }
}