package it.unisannio.ingegneriaDelSoftware.SaccheInScadenza;

import WebSocket.Decoders.NotificaSaccaInScadenzaDecoder;
import WebSocket.Decoders.SaccaWrapperDecoder;
import WebSocket.Encoders.NotificaSaccaInScadenzaEncoder;
import WebSocket.Encoders.SaccaWrapperEncoder;
import it.unisannio.ingegneriaDelSoftware.CcsRestApplication;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Beans.Sacca;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.CTT;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.CTTName;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Wrapper.SaccaWrapper;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;
import it.unisannio.ingegneriaDelSoftware.Functional.NotificaSaccaInScadenzaMaker;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Notifica;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Observer;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Subject;
import it.unisannio.ingegneriaDelSoftware.Util.Settings;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**Endpoint che permette ai CTT di collegarsi al CCS e ricevere la lista di sacche in scadenza dei vari CTT*/
@ServerEndpoint(value = "/ws/saccheInScadenza",
        encoders = {NotificaSaccaInScadenzaEncoder.class, SaccaWrapperEncoder.class},
        decoders = {NotificaSaccaInScadenzaDecoder.class, SaccaWrapperDecoder.class} )
public class WebSocketEndpointSaccheInScadenza implements Subject {

    /**Mappa statica, quindi unica, per tutte le istanze dell'endPoint che memorizza il CTT con la relativa sessione*/
    protected static ConcurrentMap<Session,String> sessioniCTT = new ConcurrentHashMap<Session,String>();
    /**observer da notificare nel momento in cui un CTT chiude la sua connessione*/
    private Observer observer = new SaccheInScadenzaObserver();

    @OnOpen
    public void start(Session session) {
        CcsRestApplication.logger.info("CTT connesso al SaccheInScadenza EndPoint");
        sessioniCTT.put(session,session.getUserPrincipal().getName());
    }

    /**Il CCS puo ricevere dal CTT soltanto il suo nome.
     * Appena ricevuto il CCS provvede a memorizzarlo nella mappa e ad inoltrare la lista delle sacche in scadenza*/
    @OnMessage
    public void receive(SaccaWrapper saccheInScadenza, Session session) {
        CcsRestApplication.logger.info("Ho ricevuto delle sacche in scadenza");
        MongoDataManager mm = MongoDataManager.getInstance();

        for (Sacca s : saccheInScadenza.getSacche()) {
            try {
                mm.createSacca(s);
            } catch (EntityAlreadyExistsException e) {
                //non fa niente
            }
        }

        this.notifyCTT(NotificaSaccaInScadenzaMaker.creaNotificheSaccheInScadenza());

    }

    /**Quando il CTT chiude la connessione, le sue sacche in scadenza sono rimosse dal CCS e vengono notificati gli altri CTT*/
    @OnClose
    public void end(Session s)  {
        CTTName cttOffline = null;
        try {
            String ip = sessioniCTT.get(s);
            for (CTT ctt : Settings.ip.keySet())
                if (Settings.ip.get(ctt).equals(ip))
                    cttOffline = ctt.getDenominazione();

            CcsRestApplication.logger.error("Ecco il CTT che si è disconnesso: "+cttOffline);
            sessioniCTT.remove(s);
            MongoDataManager.getInstance().removeSaccheCttOffline(cttOffline);
            this.notifyCTT(NotificaSaccaInScadenzaMaker.creaNotificheSaccheInScadenza());
        } catch (EntityNotFoundException e) {
            //do nothing
        }
    }

    @OnError
    public void onError(Throwable t) throws Throwable {
        CcsRestApplication.logger.error("Errore :"+ t.getMessage() );
    }

    @Override
    public void notifyCTT(List<Notifica> notifica) {
        this.observer.update(notifica);

    }
}