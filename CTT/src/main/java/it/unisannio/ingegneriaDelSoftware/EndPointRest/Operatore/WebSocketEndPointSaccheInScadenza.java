package it.unisannio.ingegneriaDelSoftware.EndPointRest.Operatore;

import WebSocket.Decoders.NotificaSaccaInScadenzaDecoder;
import WebSocket.Encoders.NotificaSaccaInScadenzaEncoder;
import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;

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
        CttDataBaseRestApplication.logger.info("Terminale Operatore Connesso al Server Endpoint sessione: "+session.getId());
        try {
            session.getBasicRemote().sendObject(SaccheInScadenzaClientEndPoint.notificheSaccheInScadenza);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }
        sessions.add(session);
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
