package it.unisannio.ingegneriaDelSoftware.EndPointRest.Magazziniere;

import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaEvasione;
import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaSmaltimentoSacche;
import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.List;


public class TerminaleMagazziniereObserver{

	/**Metodo con cui l'observer viene aggiornato passandogli una nuova lista di notificheEvasione
	 * La nuova lista viene inviata ogni sessione attiva sul WebSocketEndPointEvasioneSacche 
	 * @param notifiche la nuova lista delle notifiche evasione
	 */
    public void update(List<NotificaEvasione> notifiche) {

        try {
            for (Session s : WebSocketEndPointEvasioneSacche.sessions) {
                s.getBasicRemote().sendObject(notifiche);
                CttDataBaseRestApplication.logger.info("Ho Inviato La notifica Evasione al TerminaleMagazziniere con session " + s.getId());
            }
        }catch (EncodeException e) {
            CttDataBaseRestApplication.logger.error("Problemi con la codifica delle notifiche evasione");
        } catch (IOException e) {
            CttDataBaseRestApplication.logger.error("Problemi con l'inoltro della notifica evasione al magazziniere");
        }

    }

    /**Metodo con cui l'observer viene aggiornato passandogli la nuova notificaSmaltimentoSacche
	 * La nuova notifica viene inviata ogni sessione attiva sul WebSocketEndPointSmaltimentoSacche 
	 * @param notificaSmaltimentoSacche la nuova notifica di smaltimento
	 */
    public void update(NotificaSmaltimentoSacche notificaSmaltimentoSacche) {

        try {
            for (Session s : WebSocketEndPointSmaltimentoSacche.sessions) {
                s.getBasicRemote().sendObject(notificaSmaltimentoSacche);
                CttDataBaseRestApplication.logger.info("Ho Inviato La notifica smaltimento al TerminaleMagazziniere con session " + s.getId());
            }
        }catch (EncodeException e) {
            CttDataBaseRestApplication.logger.error("Problemi con la codifica delle notifiche smaltimento");
        } catch (IOException e) {
            CttDataBaseRestApplication.logger.error("Problemi con l'inoltro della notifica smaltimento al magazziniere");
        }

    }

}
