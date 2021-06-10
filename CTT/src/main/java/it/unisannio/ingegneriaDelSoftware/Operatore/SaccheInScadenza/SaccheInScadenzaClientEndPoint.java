package it.unisannio.ingegneriaDelSoftware.Operatore.SaccheInScadenza;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.websocket.*;

import WebSocket.Decoders.SaccaWrapperDecoder;
import WebSocket.Encoders.SaccaWrapperEncoder;
import it.unisannio.ingegneriaDelSoftware.AbstractClasses.OperatoreSubject;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche.NotificaSaccaInScadenza;
import WebSocket.Decoders.NotificaSaccaInScadenzaDecoder;
import WebSocket.Encoders.NotificaSaccaInScadenzaEncoder;
import it.unisannio.ingegneriaDelSoftware.CttRestApplication;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Observer;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Subject;
import it.unisannio.ingegneriaDelSoftware.Util.Settings;

import java.util.List;

/**Client endpoint che si collega al SaccheInScadenzaEndPoint presente sul CCS
 * registrazione di encoders e decoders
 */
@ClientEndpoint(
		encoders = {NotificaSaccaInScadenzaEncoder.class, SaccaWrapperEncoder.class},
		decoders = {NotificaSaccaInScadenzaDecoder.class, SaccaWrapperDecoder.class} )
public class SaccheInScadenzaClientEndPoint extends OperatoreSubject {

	/**Lista statica delle notifiche sacche in scadenza, viene aggiornata dal CCS */
	public static List<NotificaSaccaInScadenza> notificheSaccheInScadenza = new ArrayList<NotificaSaccaInScadenza>();
	protected static Session session;

	/**Costruttore di SaccheInScadenzaClientEndPoint, prova a collegarsi al CCS
	 * se non ci riesce viene aspettato un tempo standard prima di riprovarci (modificabile nei settings.xml)
	 * @throws InterruptedException
	 */
	public SaccheInScadenzaClientEndPoint() throws InterruptedException {
		try{
			WebSocketContainer container=ContainerProvider.getWebSocketContainer();
			container.connectToServer(this, new URI(Settings.ccsWebSocket));


		}catch(DeploymentException | URISyntaxException | IOException ex){
			CttRestApplication.logger.error("Il CCS è OFFLINE");
			Thread.sleep(1000*Settings.retry);
			new SaccheInScadenzaClientEndPoint();
		}
	}

	/**Quando viene stabilita una nuova connessione con il CCS il CTT invia il proprio nome in modo che il CCS possa mapparlo */
	@OnOpen
	public void onOpen(Session session) throws EntityNotFoundException {
		CttRestApplication.logger.info("Connesso al ServerEndpointSaccheInScadenza del CCS");
		this.session=session;
		new GestioneScadenzeCTT().alertSaccheInScadenza();
	}

	/**Quando il CCS inoltra la lista delle sacche in scadenza il CTT aggiorna la propria copia mantenuta in locale */
	@OnMessage
	public void onMessage(ArrayList<NotificaSaccaInScadenza> notifiche, Session session){
		CttRestApplication.logger.info("Il CCS ha inoltrato la lista delle sacche in scadenza");
		this.notificheSaccheInScadenza = notifiche;
		this.notifySaccheInScadenzaObserver();
	}


	/**Quando si interrompe la connessione il CTT elimina la propria copia locale delle sacche in scadenza e riprova periodicamente a collegarsi */
	@OnClose
	public void onClose(Session session) throws InterruptedException {
		try{
			notificheSaccheInScadenza.clear();
			this.notifySaccheInScadenzaObserver();
			WebSocketContainer container=ContainerProvider.getWebSocketContainer();
			container.connectToServer(this, new URI(Settings.ccsWebSocket));
			CttRestApplication.logger.error("Connessione con il EndPointSaccheInScadenza del CCS Interrotta");
		}catch(Exception ex){
			CttRestApplication.logger.error("Il CCS è OFFLINE");
			Thread.sleep(1000*Settings.retry);
			new SaccheInScadenzaClientEndPoint();
		}
	}

	@Override
	public void notifySaccheInScadenzaObserver() {
		for(Observer obs : Subject.observerList)
			if (obs instanceof SaccheInScadenzaObserver)
				obs.update();
	}


	@Override
	public void notifyRicercaGlobaleObserver() {
		//do nothing
	}

}

