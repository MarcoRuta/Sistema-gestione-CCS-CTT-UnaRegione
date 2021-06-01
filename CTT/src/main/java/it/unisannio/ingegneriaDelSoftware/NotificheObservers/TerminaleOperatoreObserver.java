package it.unisannio.ingegneriaDelSoftware.NotificheObservers;

import WebSocket.ServerEndpoint.WebSocketEndPointSaccheInScadenza;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Notifica;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Observer;
import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaSaccaInScadenza;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;

public class TerminaleOperatoreObserver implements Observer {
    @Override
    public void update(Notifica notifica) {
        try {
            if(notifica instanceof NotificaSaccaInScadenza) {
                NotificaSaccaInScadenza unaNotifica = (NotificaSaccaInScadenza) notifica;
                for (Session s : WebSocketEndPointSaccheInScadenza.sessions)
                    // getBasicRemote(), situato nella libria WebSocket, permette di realizzare la comunicazione sincrona
                    s.getBasicRemote().sendObject(unaNotifica);
            }
        }catch (EncodeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
