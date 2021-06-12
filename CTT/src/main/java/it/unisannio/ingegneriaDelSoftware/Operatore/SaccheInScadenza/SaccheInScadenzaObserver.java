package it.unisannio.ingegneriaDelSoftware.Operatore.SaccheInScadenza;

import it.unisannio.ingegneriaDelSoftware.CttRestApplication;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche.NotificaSaccaInScadenza;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Observer;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

public class SaccheInScadenzaObserver implements Observer {

    /**Supplier utilizzato per accedere in modo protetto allo stato del modello*/
    Supplier<List<NotificaSaccaInScadenza>> saccheInScadenzaSupplier;

    public SaccheInScadenzaObserver(Supplier<List<NotificaSaccaInScadenza>> saccheInScadenzaSupplier) {
        this.saccheInScadenzaSupplier = saccheInScadenzaSupplier;
    }

    @Override
    public void update() {
        List<NotificaSaccaInScadenza> saccaInScadenza = saccheInScadenzaSupplier.get();
        try {
            for (Session session : WebSocketEndPointSaccheInScadenza.sessions) {
                session.getBasicRemote().sendObject(saccaInScadenza);
                CttRestApplication.logger.info("Lista sacche inoltrata all'operatore con sessione "+session.getId());
            }
        } catch (IOException e) {
            CttRestApplication.logger.error("Non sono riuscito ad inoltrare la lista delle sacche");
        } catch (EncodeException e) {
            CttRestApplication.logger.error("Problemi con la codifica delle notifiche delle sacche in scadenza");
        }
    }
}
