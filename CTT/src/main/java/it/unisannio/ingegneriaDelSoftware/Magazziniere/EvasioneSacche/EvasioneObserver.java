package it.unisannio.ingegneriaDelSoftware.Magazziniere.EvasioneSacche;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche.NotificaEvasione;
import it.unisannio.ingegneriaDelSoftware.CttRestApplication;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Observer;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

public class EvasioneObserver implements Observer {

    private Supplier<List<NotificaEvasione>> evasioni;

    public EvasioneObserver(Supplier<List<NotificaEvasione>> evasioni) {
        this.evasioni = evasioni;
    }


    @Override
    public void update() {

        List<NotificaEvasione> evasioniDaNotificare = evasioni.get();
        try {
            for (Session s : WebSocketEndPointEvasioneSacche.sessions) {
                s.getBasicRemote().sendObject(evasioniDaNotificare);
                CttRestApplication.logger.info("Ho Inviato La notifica Evasione al TerminaleMagazziniere con session " + s.getId());
            }
        }catch (EncodeException e) {
            CttRestApplication.logger.error("Problemi con la codifica delle notifiche evasione");
        } catch (IOException e) {
            CttRestApplication.logger.error("Problemi con l'inoltro della notifica evasione al magazziniere");
        }
    }
}
