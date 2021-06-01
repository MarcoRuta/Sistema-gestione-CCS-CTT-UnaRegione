package WebSocket.ClientEndPoint;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import javax.websocket.*;

import WebSocket.ServerEndpoint.WebSocketEndPointSaccheInScadenza;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.Classes.Seriale;
import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaSaccaInScadenza;
import WebSocket.Decoders.NotificaSaccaInScadenzaDecoder;
import WebSocket.Encoders.NotificaSaccaInScadenzaEncoder;
import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;

import java.util.List;


@ClientEndpoint(
encoders = {NotificaSaccaInScadenzaEncoder.class},
decoders = {NotificaSaccaInScadenzaDecoder.class} )
public class SaccheInScadenzaClientEndPoint {

	 public static List<NotificaSaccaInScadenza> notificheSaccheInScadenza = new ArrayList<NotificaSaccaInScadenza>();


	 private final String uri= Constants.CCSWebSocket;
	 private Session session;
			  

	 public SaccheInScadenzaClientEndPoint(){
	 	try{
	 		WebSocketContainer container=ContainerProvider.getWebSocketContainer();
	 		container.connectToServer(this, new URI(uri));
	 	}catch(Exception ex){

	 	}
	 }

	 @OnOpen
	 public void onOpen(Session session){
	 	CttDataBaseRestApplication.logger.info("Connesso al ServerEndpointSaccheInScadenza Del CCS");
	 	this.session=session;
	 }

	 @OnMessage
	 public void onMessage(ArrayList<NotificaSaccaInScadenza> notifiche, Session session){
		 CttDataBaseRestApplication.logger.info("Il CCS ha inoltrato la lista delle sacche in scadenza");
	 	this.notificheSaccheInScadenza = notifiche;
	 	this.notifyOperatore();
	 }

	private void notifyOperatore() {
			try {
				for (Session session : WebSocketEndPointSaccheInScadenza.sessions) {
					session.getBasicRemote().sendObject(notificheSaccheInScadenza);
					CttDataBaseRestApplication.logger.info("Lista sacche inoltrata all'operatore con sessione "+session.getId());
				}
			} catch (IOException e) {
				CttDataBaseRestApplication.logger.error("Non sono riuscito ad inoltrare la lista delle sacche");
			} catch (EncodeException e) {
				CttDataBaseRestApplication.logger.error("Problemi con la codifica delle notifiche delle sacche in scadenza");
			}
	}

	@OnClose
	public void onClose(Session session){
		CttDataBaseRestApplication.logger.error("Connessione con il EndPointSaccheInScadenza del CCS Interrotta");
	 }

}
	

