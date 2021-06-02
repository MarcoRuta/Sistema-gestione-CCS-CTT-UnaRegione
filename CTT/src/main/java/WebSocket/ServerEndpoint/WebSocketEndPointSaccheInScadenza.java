package WebSocket.ServerEndpoint;
import WebSocket.ClientEndPoint.SaccheInScadenzaClientEndPoint;
import WebSocket.Decoders.NotificaSaccaInScadenzaDecoder;
import WebSocket.Encoders.NotificaSaccaInScadenzaEncoder;
import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ServerEndpoint(value = "/ws/saccheInScadenza",
        encoders = {NotificaSaccaInScadenzaEncoder.class},
        decoders = {NotificaSaccaInScadenzaDecoder.class} )

public class WebSocketEndPointSaccheInScadenza {
	
    public static List<Session> sessions = new ArrayList<Session>();
    private Session session;

    /**Avviato nel momento in cui il terminale operatore di un CTT si connette alla rete dei CTT
	 * @param session La sessione durante la quale il CTT è connesso
	 */
    @OnOpen
    public void start(Session session) {
        CttDataBaseRestApplication.logger.info("Terminale Operatore Connesso al Server Endpoint sessione: "+session.getId());
        this.session = session;
        try {
            session.getBasicRemote().sendObject(SaccheInScadenzaClientEndPoint.notificheSaccheInScadenza);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }
        sessions.add(session);
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