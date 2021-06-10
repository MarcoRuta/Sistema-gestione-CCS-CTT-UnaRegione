package it.unisannio.ingegneriaDelSoftware.Magazziniere.EvasioneSacche;

import WebSocket.Decoders.NotificaEvasioneDecoder;
import WebSocket.Encoders.NotificaEvasioneEncoder;
import it.unisannio.ingegneriaDelSoftware.CttRestApplication;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.EndPointRestNotificheMagazziniere;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**Registrazione del topic e di encoders e decoders sul websocket*/
@ServerEndpoint(value = "/ws/notifiche",
        encoders = {NotificaEvasioneEncoder.class},
        decoders = {NotificaEvasioneDecoder.class} )
public class WebSocketEndPointEvasioneSacche {
	
	/**Lista delle sessioni mantenute dal websocket*/
	protected static List<Session> sessions = new ArrayList<Session>();

	

	/**Quando viene stabilita una nuova connessione la websocket deve inviare alla nuova sessione la lista delle notifiche evasione */
    @OnOpen
    public void start(Session session) {
        try {
            sessions.add(session);
            CttRestApplication.logger.info("Terminale Magazziniere Connesso alla WebSocket sessione: "+session.getId());
            //gli inoltro tutte le notifiche che ho ricevuto
            session.getBasicRemote().sendObject(EndPointRestNotificheMagazziniere.getNotificheEvasione());
            CttRestApplication.logger.info("NotificheSacche in Scadenza inoltrate correttamente");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }

    /**Viene rimossa la sessione dalla lista delle sessioni*/
    @OnClose
    public void end(Session session) {
    	sessions.remove(session);
    	
    }


    @OnMessage
    public void receive(String message, Session session) {
    }

    @OnError
    public void onError(Throwable t) throws Throwable {
    }
	

}