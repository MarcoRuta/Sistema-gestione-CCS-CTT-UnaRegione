package MosquitoNotificationTry;

import javax.websocket.Session;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import WebSocketConfig.NotificaEvasioneDecoder;
import WebSocketConfig.WebSocketEndpoint;
import it.unisannio.ingegneriaDelSoftware.Classes.NotificaEvasione;

public class NotificaEvasioneSubscriber {
	
	private  MqttClient subscriber;
	private  String brokerAddress;
	
	public NotificaEvasioneSubscriber(String brokerAddress) {
		
		this.brokerAddress = brokerAddress;	
		
	}
	
	public void subscribe() throws Exception {
		
		subscriber = new MqttClient(brokerAddress, "sub1");
		subscriber.connect();
		
		subscriber.subscribe("/+/mosquitoTry", new MyListener());
		
	}
}

class MyListener implements IMqttMessageListener {
	
	/*
	 * Metodo eseguito quando il subscriber riceve un messaggio
	 */
	public void messageArrived(String topic, MqttMessage msg) {
		
		System.out.println("A message is arrived");
		System.out.println(msg);
		
		try {
			
			/* --- CODICE PER INSTRADAZIONE WB --- */
			NotificaEvasione ns = new NotificaEvasioneDecoder().decode(new String(msg.getPayload()));
		
			// invio dell'oggetto Sample 'ns' al Browser
			for (Session s : WebSocketEndpoint.sessions)
				// getBasicRemote(), situato nella libria WebSocket, permette di realizzare la comunicazione sincrona
				s.getBasicRemote().sendObject(ns);	
			
		} 
		catch(Exception e)  {System.err.println(e);}   
	}
}