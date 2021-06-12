package it.unisannio.ingegneriaDelSoftware.Operatore.RicercaGlobale;

import WebSocket.Decoders.NotificaRisultatiRicercaDecoder;
import WebSocket.Encoders.NotificaRisultatiRicercaEncoder;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche.NotificaRisultatiRicerca;
import it.unisannio.ingegneriaDelSoftware.CttRestApplication;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.EndPointRestNotificheOperatore;

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
        CttRestApplication.logger.info("Terminale Operatore Connesso al Server Endpoint RicercaGlobale sessione: " + session.getId());
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
        //No message supported
    }

    @OnError
    public void onError(Throwable t) throws Throwable {
        CttRestApplication.logger.error(t.getMessage());
    }

    /**Vengono inviati i risultati della ricerca globale alla sessione relativa ad un terminale dell'operatore */
    private void notifyOperatore(Session session) {
        try {
            if (EndPointRestNotificheOperatore.getNotificaRisultatiRicerca() != null) {
                session.getBasicRemote().sendObject(EndPointRestNotificheOperatore.getNotificaRisultatiRicerca());
                EndPointRestNotificheOperatore.removeNotificaRisultatiRicercaGlobale();
            }
        } catch (IOException e) {
            CttRestApplication.logger.error("Non sono riuscito ad inoltrare notificaRisultatiRicerca all'operatore con sessione: " + session.getId());
        } catch (EncodeException e) {
            CttRestApplication.logger.error("Non sono riuscito a serializzare notificaRisultatiRicerca ");
        }
    }
}

