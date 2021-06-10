package it.unisannio.ingegneriaDelSoftware.Magazziniere.SmaltimentoSacche;

import it.unisannio.ingegneriaDelSoftware.CttRestApplication;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche.NotificaSmaltimentoSacche;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Observer;
import it.unisannio.ingegneriaDelSoftware.Magazziniere.SmaltimentoSacche.WebSocketEndPointSmaltimentoSacche;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.function.Supplier;

public class SmaltimentoObserver implements Observer {

    Supplier<NotificaSmaltimentoSacche> saccheDaSmaltire;

    public SmaltimentoObserver(Supplier<NotificaSmaltimentoSacche> saccaDaSmaltire) {
        this.saccheDaSmaltire = saccaDaSmaltire;
    }


    @Override
    public void update() {
        NotificaSmaltimentoSacche notificaSmaltimentoSacche = saccheDaSmaltire.get();
        try {
            for (Session s : WebSocketEndPointSmaltimentoSacche.sessions) {
                s.getBasicRemote().sendObject(notificaSmaltimentoSacche);
                CttRestApplication.logger.info("Ho inviato La notifica smaltimento al TerminaleMagazziniere con session " + s.getId());
            }
        }catch (EncodeException e) {
            CttRestApplication.logger.error("Problemi con la codifica delle notifiche smaltimento");
        } catch (IOException e) {
            CttRestApplication.logger.error("Problemi con l'inoltro della notifica smaltimento al magazziniere");
        }
    }
}
