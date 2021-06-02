package it.unisannio.ingegneriaDelSoftware.EndPointRest.Operatore;

import WebSocket.Decoders.NotificaSaccaInScadenzaDecoder;
import WebSocket.Encoders.NotificaSaccaInScadenzaEncoder;
import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ServerEndpoint(value = "/ws/saccheInScadenza",
        encoders = {NotificaSaccaInScadenzaEncoder.class},
        decoders = {NotificaSaccaInScadenzaDecoder.class} )

public class WebSocketEndPointSaccheInScadenza {
    public static List<Session> sessions = new ArrayList<Session>();


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


    @OnClose
    public void end(Session session) {
        sessions.remove(session);

    }


    @OnMessage
    public void receive(String message, Session session) {
        // in this example we don't receive messages with this endpoint
    }

    @OnError
    public void onError(Throwable t) throws Throwable {
        // to write for handling errors
    }

}
