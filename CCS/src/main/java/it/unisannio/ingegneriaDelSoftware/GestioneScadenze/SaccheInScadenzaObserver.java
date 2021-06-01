package it.unisannio.ingegneriaDelSoftware.GestioneScadenze;

import java.io.IOException;
import java.util.List;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import WebSocket.ServerEndpoint.WebSocketEndpointSaccheInScadenza;
import it.unisannio.ingegneriaDelSoftware.CcsDataBaseRestApplication;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Notifica;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Observer;

public class SaccheInScadenzaObserver implements Observer {

	@Override
	public void update(List<Notifica> notifiche) {
		for(Session s : WebSocketEndpointSaccheInScadenza.sessions) {
			try {
				s.getBasicRemote().sendObject(notifiche);
				CcsDataBaseRestApplication.logger.info("Ho inviato la lista delle sacche in scadenza alla session "+s.getId());
			} catch (IOException e) {
				CcsDataBaseRestApplication.logger.error("Non sono riuscito ad inoltrare la lista delle sacche");
				e.printStackTrace();
			} catch (EncodeException e) {
				CcsDataBaseRestApplication.logger.error("Problemi con la codifica delle notifiche delle sacche in scadenza");
				e.printStackTrace();
			}
		}
		
	}
		

}
