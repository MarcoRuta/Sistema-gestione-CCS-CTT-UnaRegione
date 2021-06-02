package it.unisannio.ingegneriaDelSoftware.NotificheObservers;

import WebSocket.ServerEndpoint.WebSocketEndPointEvasioneSacche;
import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaEvasione;
import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Notifica;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Observer;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TerminaleMagazziniereObserver implements Observer {

    public void update(Notifica notifica) {
        try {
                if (notifica instanceof  NotificaEvasione) {
                    NotificaEvasione notificaEvasione = (NotificaEvasione) notifica;
                    for (Session s : WebSocketEndPointEvasioneSacche.sessions) {
                        s.getBasicRemote().sendObject(notificaEvasione);
                        CttDataBaseRestApplication.logger.info("Ho Inviato La notifica Evasione al TerminaleMagazziniere con session " + s.getId());
                    }
                }
        }catch (EncodeException e) {
            CttDataBaseRestApplication.logger.error("Problemi con la codifica delle notifiche evasione");
        } catch (IOException e) {
            CttDataBaseRestApplication.logger.error("Problemi con l'inoltro della notifica evasione al magazziniere");
        }

    }
}
