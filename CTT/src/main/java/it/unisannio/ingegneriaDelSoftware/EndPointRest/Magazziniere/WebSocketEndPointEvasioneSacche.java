package it.unisannio.ingegneriaDelSoftware.EndPointRest.Magazziniere;

import WebSocket.Decoders.NotificaEvasioneDecoder;
import WebSocket.Encoders.NotificaEvasioneEncoder;
import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.Magazziniere.EndPointNotificheMagazziniere;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/ws/notifiche",
        encoders = {NotificaEvasioneEncoder.class},
        decoders = {NotificaEvasioneDecoder.class} )
public class WebSocketEndPointEvasioneSacche {
	
	protected static List<Session> sessions = new ArrayList<Session>();

	

    @OnOpen
    public void start(Session session) {
        try {
            sessions.add(session);
            CttDataBaseRestApplication.logger.info("Terminale Magazziniere Connesso alla WebSocket sessione: "+session.getId());
            //gli inoltro tutte le notifiche che ho ricevuto
            session.getBasicRemote().sendObject(EndPointNotificheMagazziniere.getNotificheEvasione());
            CttDataBaseRestApplication.logger.info("NotificheSacche in Scadenza inoltrate correttamente");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }
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