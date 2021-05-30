package it.unisannio.ingegneriaDelSoftware.SaccheInScadenzaManager;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DecodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;

import java.util.List;


@ClientEndpoint(
encoders = {NotificaSaccaInScadenzaEncoder.class},
decoders = {NotificaSaccaInScadenzaDecoder.class} )
public class SaccheInScadenzaClient {
	
	// private ClientEndpointConfig clientConfig;
	 private List<NotificaSaccaInScadenza> notificheSaccheInScadenza = new ArrayList<NotificaSaccaInScadenza>();

	   private final String uri="ws://localhost:8080/ws/saccheInScadenza";
			   private Session session;
			  

			   public SaccheInScadenzaClient(){
			      try{
			         WebSocketContainer container=ContainerProvider.getWebSocketContainer();
			         container.connectToServer(this, new URI(uri));

			      }catch(Exception ex){

			      }
			   }

			   @OnOpen
			   public void onOpen(Session session){
				  System.err.println("Connessione creata"); 
			      this.session=session;
			   }

			   @OnMessage
			   public void onMessage(ArrayList<NotificaSaccaInScadenza> notifiche, Session session){
			   
				   this.notificheSaccheInScadenza = notifiche;
				   System.err.println(this.notificheSaccheInScadenza + "\nAOOOO");
				
			      
			   }
			   @OnClose
			   public void onClose(Session session){
				   System.err.println("Connessione chiusa");
				   
			   }
 
	}
	

