package it.unisannio.ingegneriaDelSoftware.NotificheObservers;

import WebSocketConfig.WebSocketEndpoint;
import it.unisannio.ingegneriaDelSoftware.Classes.NotificaEvasione;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Notifica;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Observer;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;


public class TerminaleMagazziniereObserver implements Observer {

    public void update(Notifica notifica) {
        try {
           if(notifica instanceof NotificaEvasione) {
               NotificaEvasione unaNotifica = (NotificaEvasione) notifica;
               for (Session s : WebSocketEndpoint.sessions)
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
