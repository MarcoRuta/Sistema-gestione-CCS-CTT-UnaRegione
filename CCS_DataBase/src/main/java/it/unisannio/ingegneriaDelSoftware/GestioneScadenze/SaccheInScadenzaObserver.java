package it.unisannio.ingegneriaDelSoftware.GestioneScadenze;

import java.io.IOException;
import java.util.List;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import WebSocketConfig.WebSocketEndpoint;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Notifica;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Observer;

public class SaccheInScadenzaObserver implements Observer {

	@Override
	public void update(List<Notifica> notifiche) {
		for(Session s : WebSocketEndpoint.sessions) {
			try {
				System.err.println("HO MANDATOO");
				
				s.getBasicRemote().sendObject(notifiche);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (EncodeException e) {
				e.printStackTrace();
			}
		}
		
	}
		

}
