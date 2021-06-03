package it.unisannio.ingegneriaDelSoftware.EndPointRest.Magazziniere;

import WebSocket.Decoders.NotificaSmaltimentoSaccheDecoder;
import WebSocket.Encoders.NotificaSmaltimentoSaccheEncoder;
import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**Registrazione del topic e di encoders e decoders sul websocket*/
@ServerEndpoint(value = "/ws/SmaltimentoSacche",
        encoders = {NotificaSmaltimentoSaccheEncoder.class},
        decoders = {NotificaSmaltimentoSaccheDecoder.class} )

public class WebSocketEndPointSmaltimentoSacche {
	
	/**Lista delle sessioni mantenute dal websocket*/
    public static List<Session> sessions = new ArrayList<Session>();

    /**Quando viene stabilita una nuova connessione la websocket deve inviare alla nuova sessione la notifica di smaltimento, se presente! */
    @OnOpen
    public void start(Session session) {
        CttDataBaseRestApplication.logger.info("Terminale Magazziniere Connesso al Server Endpoint Smaltimento sessione: " + session.getId());
        try {
            if(EndPointNotificheMagazziniere.serialiDaSmaltire != null) {
                session.getBasicRemote().sendObject(EndPointNotificheMagazziniere.serialiDaSmaltire);
                CttDataBaseRestApplication.logger.info("Ho inviato la notifica di smaltimento sacca" + session.getId());
                CttDataBaseRestApplication.logger.info("notifica: " + EndPointNotificheMagazziniere.serialiDaSmaltire);
            }
            sessions.add(session);
        } catch (IOException e) {
            CttDataBaseRestApplication.logger.error("Non sono riuscito ad inviare la lista smaltimento: "+e.getMessage());
        } catch (EncodeException e) {
            CttDataBaseRestApplication.logger.error("Problemi nell'encoding della notifica smaltimento: "+e.getMessage());
        }

    }

    /**Viene rimossa la sessione dalla lista delle sessioni*/
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
