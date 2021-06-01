package WebSocket.ServerEndpoint;
import WebSocket.Decoders.NotificaSaccaInScadenzaDecoder;
import WebSocket.Encoders.NotificaSaccaInScadenzaEncoder;
import it.unisannio.ingegneriaDelSoftware.CcsDataBaseRestApplication;
import it.unisannio.ingegneriaDelSoftware.Functional.NotificaSaccaInScadenzaMaker;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/ws/saccheInScadenza",
        encoders = {NotificaSaccaInScadenzaEncoder.class},
        decoders = {NotificaSaccaInScadenzaDecoder.class} )
public class WebSocketEndpointSaccheInScadenza {
	
	public static List<Session> sessions = new ArrayList<Session>();
	private Session session;
	
	/**Metodo avviato nel momento in cui un CTT si connette alla rete dei CTT
	 * @param session La sessione durante la quale il CTT è connesso
	 */
    @OnOpen
    public void start(Session session) {
        CcsDataBaseRestApplication.logger.info("CTT connesso al SaccheInScadenza EndPoint");
        try {
            this.session = session;
            session.getBasicRemote().sendObject(NotificaSaccaInScadenzaMaker.creaNotificheSaccheInScadenza());
            sessions.add(session);
            CcsDataBaseRestApplication.logger.info("Ho inoltrato al CTT la lista delle sacche in scadenza");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }

    /**Metodo avviato nel momento in cui un CTT esce dalla rete dei CTT 
     */
    @OnClose
    public void end() {
    	sessions.remove(session);
    }

    
    /**Riceve i messaggi provenienti dalla rete dei CTT
     * @param message
     */
    @OnMessage
    public void receive(String message) {
        // Implementato nel branch
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