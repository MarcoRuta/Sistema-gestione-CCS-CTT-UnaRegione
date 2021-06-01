package WebSocket.ServerEndpoint;

import WebSocket.Decoders.CTTNameDecoder;
import WebSocket.Decoders.NotificaSaccaInScadenzaDecoder;
import WebSocket.Encoders.CTTNameEncoder;
import WebSocket.Encoders.NotificaSaccaInScadenzaEncoder;
import it.unisannio.ingegneriaDelSoftware.CcsDataBaseRestApplication;
import it.unisannio.ingegneriaDelSoftware.Classes.CTTName;
import it.unisannio.ingegneriaDelSoftware.Functional.NotificaSaccaInScadenzaMaker;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import it.unisannio.ingegneriaDelSoftware.GestioneScadenze.SaccheInScadenzaObserver;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Observer;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;



@ServerEndpoint(value = "/ws/saccheInScadenza",
        encoders = {NotificaSaccaInScadenzaEncoder.class, CTTNameEncoder.class},
        decoders = {NotificaSaccaInScadenzaDecoder.class, CTTNameDecoder.class} )
public class WebSocketEndpointSaccheInScadenza {
	
	public static ConcurrentMap<CTTName,Session> sessioniCTT = new ConcurrentHashMap<CTTName,Session>();
	
	private Observer observer = new SaccheInScadenzaObserver();


    @OnOpen
    public void start(Session session) {
        CcsDataBaseRestApplication.logger.info("CTT connesso al SaccheInScadenza EndPoint");
    }


    @OnMessage
    public void receive(CTTName cttName, Session session) {
    	try {
        	sessioniCTT.put(cttName, session);
        	CcsDataBaseRestApplication.logger.info("CTT: "+cttName.getCttname()+" connesso con sessione: "+session.getId());
			CcsDataBaseRestApplication.logger.info("Ecco la lista dei CTT connessi "+ sessioniCTT);
			session.getBasicRemote().sendObject(NotificaSaccaInScadenzaMaker.creaNotificheSaccheInScadenza());
			CcsDataBaseRestApplication.logger.info("Ho inoltrato al "+cttName.getCttname()+"la lista delle sacche in scadenza");
		} catch (IOException | EncodeException e) {
			e.printStackTrace();
			CcsDataBaseRestApplication.logger.info("Non sono riuscito a creare una connessione con il CTT con nome:" +cttName.getCttname());
		}
        
        
    }


	@OnClose
	public void end(Session s)  {
		try {
    		CTTName cttOffline = null;
			for (CTTName name : sessioniCTT.keySet())
				if (sessioniCTT.get(name).equals(s)) {
					cttOffline = name;
					CcsDataBaseRestApplication.logger.info("CTT: " + name.getCttname() + " Disconnesso con sessione: " + s.getId());
					sessioniCTT.remove(name);
				}

			MongoDataManager.getInstance().removeSaccheCttOffline(cttOffline);

			this.observer.update(NotificaSaccaInScadenzaMaker.creaNotificheSaccheInScadenza());
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    @OnError
    public void onError(Throwable t) throws Throwable {
		CcsDataBaseRestApplication.logger.error("Errore :"+ t.getMessage() );
    }
	

}