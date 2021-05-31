package WebSocket.ServerEndpoint;

import WebSocket.Decoders.NotificaSaccaInScadenzaDecoder;
import WebSocket.Encoders.NotificaSaccaInScadenzaEncoder;
import it.unisannio.ingegneriaDelSoftware.CcsDataBaseRestApplication;
import it.unisannio.ingegneriaDelSoftware.Functional.NotificaSaccaInScadenzaMaker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;



@ServerEndpoint(value = "/ws/saccheInScadenza",
        encoders = {NotificaSaccaInScadenzaEncoder.class},
        decoders = {NotificaSaccaInScadenzaDecoder.class} )
public class WebSocketEndpointSaccheInScadenza {
	
	
	public static List<Session> sessions = new ArrayList<Session>();
	
	
	private Session session; 
	

    @OnOpen
    public void start(Session session) {
        CcsDataBaseRestApplication.logger.info("CTT connesso al SaccheInScadenza EndPoint");
        try {
            this.session = session;
            session.getBasicRemote().sendObject(NotificaSaccaInScadenzaMaker.creaNotificheSaccheInScadenza());
            sessions.add(session);
            CcsDataBaseRestApplication.logger.info("Ho inoltrato al CTT la lista delle sacche in scadenza");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }

    }

   
    @OnClose
    public void end() {
    	sessions.remove(session);
    	
    }


    @OnMessage
    public void receive(String message) {
        // in this example we don't receive messages with this endpoint
    }

    @OnError
    public void onError(Throwable t) throws Throwable {
        // to write for handling errors
    }
	

}