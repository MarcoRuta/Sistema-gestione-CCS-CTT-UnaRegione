package WebSocket.ServerEndpoint;
import WebSocket.Decoders.NotificaEvasioneDecoder;
import WebSocket.Encoders.NotificaEvasioneEncoder;
import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/ws/notifiche",
        encoders = {NotificaEvasioneEncoder.class},
        decoders = {NotificaEvasioneDecoder.class} )
public class WebSocketEndPointEvasioneSacche {
	
	public static List<Session> sessions = new ArrayList<Session>();
	private Session session; 
	
	
	/**Avviato nel momento in cui il terminale magazziniere di un CTT si connette alla rete dei CTT
	 * @param session La sessione durante la quale il CTT è connesso
	 */
    @OnOpen
    public void start(Session session) {
        this.session = session;
        sessions.add(session);
        CttDataBaseRestApplication.logger.info("Terminale Magazziniere Connesso alla WebSocket sessione: "+session.getId());
    }

    
    /**Avviato nel momento in cui un CTT esce dalla rete dei CTT 
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
    	// Implementato nel branch(forse)
    }

    
    /**Lancia l'eccezione al verificarsi di un errore
     * @param t L'eccezione da lanciare
     * @throws Throwable
     */
    @OnError
    public void onError(Throwable t) throws Throwable {
    	// Implementato nel branch(forse)
    }
}