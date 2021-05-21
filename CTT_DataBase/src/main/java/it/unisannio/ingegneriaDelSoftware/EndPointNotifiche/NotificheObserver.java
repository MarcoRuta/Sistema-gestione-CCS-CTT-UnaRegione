package it.unisannio.ingegneriaDelSoftware.EndPointNotifiche;

import WebSocketConfig.WebSocketEndpoint;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Notifica;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Observable;

public class NotificheObserver implements Observer {

    public void update(Notifica notifica) {
        assert notifica instanceof Notifica;

        try {
            Notifica unaNotifica = (Notifica) notifica;
            for (Session s : WebSocketEndpoint.sessions)
                // getBasicRemote(), situato nella libria WebSocket, permette di realizzare la comunicazione sincrona
                s.getBasicRemote().sendObject(unaNotifica);
        }catch (EncodeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
