package it.unisannio.ingegneriaDelSoftware.EndPointRest.Operatore.RicercaGlobale;

import WebSocket.Decoders.NotificaRisultatiRicercaDecoder;
import WebSocket.Encoders.NotificaRisultatiRicercaEncoder;
import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaRisultatiRicerca;
import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@ServerEndpoint(value ="/ws/rispostaRicercaGlobale",
        encoders = {NotificaRisultatiRicercaEncoder.class},
        decoders = {NotificaRisultatiRicercaDecoder.class} )
public class WebSocketEndPointResultRicercaGlobale {

    /**
     * Lista delle sessioni mantenute dal websocket
     */
    public static List<Session> sessions = new ArrayList<Session>();


    /**
     * Quando viene stabilita una nuova connessione la websocket deve inviare alla nuova sessione la lista delle notifiche evasione
     */
    @OnOpen
    public void start(Session session) {
        CttDataBaseRestApplication.logger.info("Terminale Operatore Connesso al Server Endpoint RicercaGlobale sessione: " + session.getId());
        this.notifyOperatore(session);
        sessions.add(session);
    }


    /**
     * Viene rimossa la sessione dalla lista delle sessioni
     */
    @OnClose
    public void end(Session session) {
        sessions.remove(session);

    }


    @OnMessage
    public void receive(NotificaRisultatiRicerca noitificaRisultatiRicerca, Session session) {
    }

    @OnError
    public void onError(Throwable t) throws Throwable {
    }


    private void notifyOperatore(Session session) {
        try {
            if (EndPointNotificheOperatore.notificaRisultatiRicerca != null) {
                session.getBasicRemote().sendObject(EndPointNotificheOperatore.notificaRisultatiRicerca);
                //devo inviarlo solo una volta
                EndPointNotificheOperatore.notificaRisultatiRicerca = null;
            }
        } catch (IOException e) {
            CttDataBaseRestApplication.logger.error("Non sono riuscito ad inoltrare notificaRisultatiRicerca all'operatore con sessione: " + session.getId());
        } catch (EncodeException e) {
            CttDataBaseRestApplication.logger.error("Non sono riuscito a serializzare notificaRisultatiRicerca ");
        }
    }
}

