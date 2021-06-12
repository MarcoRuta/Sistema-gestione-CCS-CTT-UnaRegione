package it.unisannio.ingegneriaDelSoftware.SaccheInScadenza;

import java.io.IOException;
import java.util.List;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import it.unisannio.ingegneriaDelSoftware.CcsRestApplication;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Notifica;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Observer;

public class SaccheInScadenzaObserver implements Observer {

	/** Metodo che adotta il pattern Observer per notificare tutti i CTT ogni volta che varia la lista delle sacche in scadenza
	 * @param notifiche
	 * @throws IOException, EncodeException
	 */
	@Override
	public void update(List<Notifica> notifiche) {
		for(Session s : WebSocketEndpointSaccheInScadenza.sessioniCTT.keySet()) {
			try {
				s.getBasicRemote().sendObject(notifiche);
				CcsRestApplication.logger.info("Ho inviato la lista delle sacche in scadenza alla sessione "+s.getId());
			} catch (IOException e) {
				CcsRestApplication.logger.error("Non sono riuscito ad inoltrare la lista delle sacche");
				e.printStackTrace();
			} catch (EncodeException e) {
				CcsRestApplication.logger.error("Problemi con la codifica delle notifiche delle sacche in scadenza");
				e.printStackTrace();
			}
		}
	}
}