package it.unisannio.ingegneriaDelSoftware.Operatore.SaccheInScadenza;

import WebSocket.Decoders.NotificaSaccaInScadenzaDecoder;
import WebSocket.Encoders.NotificaSaccaInScadenzaEncoder;
import it.unisannio.ingegneriaDelSoftware.CttRestApplication;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**Registrazione del topic e di encoders e decoders sul websocket*/
@ServerEndpoint(value = "/ws/saccheInScadenza",
        encoders = {NotificaSaccaInScadenzaEncoder.class},
        decoders = {NotificaSaccaInScadenzaDecoder.class} )

public class WebSocketEndPointSaccheInScadenza {
	
	/**Lista delle sessioni mantenute dal websocket*/
    public static List<Session> sessions = new ArrayList<Session>();


    /**Quando viene stabilita una nuova connessione la websocket deve inviare alla nuova sessione la lista delle notifiche evasione */
    @OnOpen
    public void start(Session session) {
        CttRestApplication.logger.info("Terminale Operatore Connesso al Server Endpoint sessione: "+session.getId());
        try {
            session.getBasicRemote().sendObject(SaccheInScadenzaClientEndPoint.notificheSaccheInScadenza);
            sessions.add(session);
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
        //No message supported
    }

    @OnError
    public void onError(Throwable t) throws Throwable {
        CttRestApplication.logger.error(t.getMessage());
    }

}
