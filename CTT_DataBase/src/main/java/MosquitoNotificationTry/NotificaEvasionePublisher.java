package MosquitoNotificationTry;
import org.eclipse.paho.client.mqttv3.MqttClient;

import com.google.gson.Gson;

import it.unisannio.ingegneriaDelSoftware.Classes.NotificaEvasione;

public class NotificaEvasionePublisher implements Runnable {
	
	public static MqttClient publisher;
	private NotificaEvasione notifica;

	@Override
	public void run() {
		try{		
			System.out.println("-----AVVIATO PUBLISHER-----");
			String brokerAddress = "tcp://127.0.0.1:1883";
			publisher = new MqttClient(brokerAddress, "pub");
			publisher.connect();

			String json=new Gson().toJson(notifica.getListaSeriali());

			publisher.publish("/1/mosquitoTry", new String("{\"listaSeriali\" : "+ json +
																	  ", \"enteRichiedente\" : \""+notifica.getEnteRichiedente()+
																	  "\", \"indirizzoEnte\" : \""+notifica.getIndirizzoEnte()+"\"}").getBytes(), 1, false);
		}catch(Exception e) {}  
	}

	public NotificaEvasione getNotifica() {
		return notifica;
	}

	public void setNotifica(NotificaEvasione notifica) {
		this.notifica = notifica;
	}
}
