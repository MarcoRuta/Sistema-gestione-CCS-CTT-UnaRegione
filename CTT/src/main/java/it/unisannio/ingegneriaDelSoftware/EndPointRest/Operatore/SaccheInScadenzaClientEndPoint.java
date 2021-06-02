package it.unisannio.ingegneriaDelSoftware.EndPointRest.Operatore;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import javax.websocket.*;

import WebSocket.Decoders.CTTNameDecoder;
import WebSocket.Encoders.CTTNameEncoder;
import it.unisannio.ingegneriaDelSoftware.Classes.CTTName;
import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaSaccaInScadenza;
import WebSocket.Decoders.NotificaSaccaInScadenzaDecoder;
import WebSocket.Encoders.NotificaSaccaInScadenzaEncoder;
import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;
import it.unisannio.ingegneriaDelSoftware.Util.Settings;

import java.util.List;


@ClientEndpoint(
encoders = {NotificaSaccaInScadenzaEncoder.class, CTTNameEncoder.class},
decoders = {NotificaSaccaInScadenzaDecoder.class, CTTNameDecoder.class} )
public class SaccheInScadenzaClientEndPoint {

	 public static List<NotificaSaccaInScadenza> notificheSaccheInScadenza = new ArrayList<NotificaSaccaInScadenza>();
	 private Session session;
			  

	 public SaccheInScadenzaClientEndPoint() throws InterruptedException {
	 	try{
				WebSocketContainer container=ContainerProvider.getWebSocketContainer();
				container.connectToServer(this, new URI(Settings.getInstance().ccsWebSocket));

	 	}catch(Exception ex){
			CttDataBaseRestApplication.logger.error("Il CCS è OFFLINE");
			Thread.sleep(1000*Settings.getInstance().retry);
			new SaccheInScadenzaClientEndPoint();
	 	}
	 }

	 @OnOpen
	 public void onOpen(Session session){
	 	CttDataBaseRestApplication.logger.info("Connesso al ServerEndpointSaccheInScadenza Del CCS");
	 	this.session=session;
		 try {
			 session.getBasicRemote().sendObject(CTTName.getInstance());
		 } catch (IOException e) {
			 e.printStackTrace();
		 } catch (EncodeException e) {
			 e.printStackTrace();
		 }
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
	public void onClose(Session session) throws InterruptedException {
		try{
			WebSocketContainer container=ContainerProvider.getWebSocketContainer();
			container.connectToServer(this, new URI(Settings.getInstance().ccsWebSocket));
			CttDataBaseRestApplication.logger.error("Connessione con il EndPointSaccheInScadenza del CCS Interrotta");
		}catch(Exception ex){
			CttDataBaseRestApplication.logger.error("Il CCS è OFFLINE");
			Thread.sleep(1000*Settings.getInstance().retry);
			new SaccheInScadenzaClientEndPoint();
		}
	 }

}
	

