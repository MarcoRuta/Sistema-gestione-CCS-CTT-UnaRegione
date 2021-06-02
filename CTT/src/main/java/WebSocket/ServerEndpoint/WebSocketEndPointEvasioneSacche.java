package WebSocket.ServerEndpoint;

import WebSocket.Decoders.NotificaEvasioneDecoder;
import WebSocket.Encoders.NotificaEvasioneEncoder;
import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaEvasione;
import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/ws/notifiche",
        encoders = {NotificaEvasioneEncoder.class},
        decoders = {NotificaEvasioneDecoder.class} )
public class WebSocketEndPointEvasioneSacche {
	
	public static List<Session> sessions = new ArrayList<Session>();

	

    @OnOpen
    public void start(Session session) {
        sessions.add(session);
        CttDataBaseRestApplication.logger.info("Terminale Magazziniere Connesso alla WebSocket sessione: "+session.getId());
        //gli inoltro tutte le notifiche che ho ricevuto
    }

   
    @OnClose
    public void end(Session session) {
    	sessions.remove(session);
    	
    }


    @OnMessage
    public void receive(String message, Session session) {
        //we don't receive messages with this endpoint
    }

    @OnError
    public void onError(Throwable t) throws Throwable {
        // to write for handling errors
    }
	

}