package WebSocket.ClientEndPoint;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import javax.websocket.*;
import WebSocket.ServerEndpoint.WebSocketEndPointSaccheInScadenza;
import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaSaccaInScadenza;
import WebSocket.Decoders.NotificaSaccaInScadenzaDecoder;
import WebSocket.Encoders.NotificaSaccaInScadenzaEncoder;
import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;
import java.util.List;

@ClientEndpoint(
encoders = {NotificaSaccaInScadenzaEncoder.class},
decoders = {NotificaSaccaInScadenzaDecoder.class} )
public class SaccheInScadenzaClientEndPoint {

	 public static List<NotificaSaccaInScadenza> notificheSaccheInScadenza = new ArrayList<NotificaSaccaInScadenza>();
	 private final String uri= Constants.CCSWebSocket;
	 private Session session;
			  
	 /**Costruttore dell'endPoint del client per avviare la connessione
	  */
	 public SaccheInScadenzaClientEndPoint(){
	 	try{
	 		WebSocketContainer container=ContainerProvider.getWebSocketContainer();
	 		container.connectToServer(this, new URI(uri));
	 	}catch(Exception ex){

	 	}
	 }

	 
	 /**Avviato nel momento in cui un CTT si connette alla rete dei CTT
		 * @param session La sessione durante la quale il CTT è connesso
		 */
	 @OnOpen
	 public void onOpen(Session session){
	 	CttDataBaseRestApplication.logger.info("Connesso al ServerEndpointSaccheInScadenza Del CCS");
	 	this.session=session;
	 }

	 
	 /**Riceve dalla rete dei CTT un ArrayList<NotificaSaccaInScadenza>
	     * @param notifiche lista delle sacche in scadenza
	     * @param session Sessione di lavoro del CTT
	     */
	 @OnMessage
	 public void onMessage(ArrayList<NotificaSaccaInScadenza> notifiche, Session session){
		 CttDataBaseRestApplication.logger.info("Il CCS ha inoltrato la lista delle sacche in scadenza");
	 	this.notificheSaccheInScadenza = notifiche;
	 	this.notifyOperatore();
	 }

	 
	private void notifyOperatore() {
		try {
			for (Session session : WebSocketEndPointSaccheInScadenza.sessions) {
				session.getBasicRemote().sendObject(notificheSaccheInScadenza);
				CttDataBaseRestApplication.logger.info("Lista sacche inoltrata all'operatore con sessione "+session.getId());
			}
		} catch (IOException e) {
			CttDataBaseRestApplication.logger.error("Non sono riuscito ad inoltrare la lista delle sacche");
		} catch (EncodeException e) {
			CttDataBaseRestApplication.logger.error("Problemi con la codifica delle notifiche delle sacche in scadenza");
		}
	}

	/**Avviato nel momento in cui un CTT esce dalla rete dei CTT 
     */
	@OnClose
	public void onClose(Session session){
		CttDataBaseRestApplication.logger.error("Connessione con il EndPointSaccheInScadenza del CCS Interrotta");
	 }
	
	
	/**Lancia l'eccezione al verificarsi di un errore
     * @param t L'eccezione da lanciare
     * @throws Throwable
     */
	@OnError
	public void onError(Throwable t) throws Throwable {
        // Implementato nel branch
    }
}