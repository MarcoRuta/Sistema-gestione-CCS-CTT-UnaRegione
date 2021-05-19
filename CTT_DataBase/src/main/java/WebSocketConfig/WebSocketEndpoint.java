package WebSocketConfig;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/ws/mosquitoTry",
        encoders = {NotificaEvasioneEncoder.class},
        decoders = {NotificaEvasioneDecoder.class} )

public class WebSocketEndpoint {
	
	public static List<Session> sessions = new ArrayList<Session>();
	private Session session; 
	

    @OnOpen
    public void start(Session session) {
    	// for each client connection a new Session is created and put in the collection
        this.session = session;
        sessions.add(session);
        System.out.println("A connection has been established");
    }

   
    @OnClose
    public void end() {
    	sessions.remove(session);
    	
    }


    @OnMessage
    public void receive(String message) {
        // in this example we don't receive messages with this endpoint
    }

    @OnError
    public void onError(Throwable t) throws Throwable {
        // to write for handling errors
    }
	

}