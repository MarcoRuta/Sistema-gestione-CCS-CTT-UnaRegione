package WebSocket.Encoders;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import com.google.gson.Gson;
import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaEvasione;
import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;

public class NotificaEvasioneEncoder implements Encoder.Text<NotificaEvasione>{

	private static Gson gson = new Gson();

	/**Restituisce l'oggetto json correttamente serializzato, dato una NotificaEvasioneSacca
	 * @param NotificaEvasioneSacca notifica corretta evasione sacca sul terminale magazziniere CTT
	 * @return Il json corrispondente alla notificaEvasioneSacca
	 */
	public String encode(NotificaEvasione notifica) throws EncodeException {
		String json = gson.toJson(notifica, NotificaEvasione.class);
		CttDataBaseRestApplication.logger.info("ho serializzato correttamente la notificaEvasione: "+notifica);
	    return json;
	}
	
	/**Inizializzatore della WebSocket*/
	public void init(EndpointConfig ec) {}
	
	
	/**Chiusura della WebSocket*/
	public void destroy() {}
}