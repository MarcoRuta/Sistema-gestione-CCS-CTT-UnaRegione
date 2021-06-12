package it.unisannio.ingegneriaDelSoftware.Operatore.RicercaGlobale;

import it.unisannio.ingegneriaDelSoftware.CttRestApplication;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche.NotificaRisultatiRicerca;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Observer;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.function.Supplier;

public class RisultatiRicercaGlobaleObserver implements Observer {

    /**Supplier utilizzato per accedere in modo protetto allo stato del modello*/
    Supplier<NotificaRisultatiRicerca> risultatiRicercaSupplier;

    public RisultatiRicercaGlobaleObserver(Supplier<NotificaRisultatiRicerca> risultatiRicercaSupplier) {
        this.risultatiRicercaSupplier = risultatiRicercaSupplier;
    }

    @Override
    public void update() {
        NotificaRisultatiRicerca risultatiRicerca = risultatiRicercaSupplier.get();

        for (Session s : WebSocketEndPointResultRicercaGlobale.sessions) {
            try {
                s.getBasicRemote().sendObject(risultatiRicerca);
                CttRestApplication.logger.info("Notifica risultati ricerca inoltrata correttamente al terminale operatore con sessione"+s.getId());
            } catch (IOException e) {
                CttRestApplication.logger.error("Impossibile inoltrare al'operatore con sessione "+s.getId()+" i risultati della ricerca");
            } catch (EncodeException e) {
                CttRestApplication.logger.error("Impossibile  serializzare la lista delle sacche trovate online con sessione "+s.getId());
            }
        }

    }
}
