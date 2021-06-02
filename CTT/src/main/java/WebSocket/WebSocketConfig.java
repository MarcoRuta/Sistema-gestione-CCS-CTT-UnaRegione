package WebSocket;
import WebSocket.ServerEndpoint.WebSocketEndPointEvasioneSacche;
import WebSocket.ServerEndpoint.WebSocketEndPointSaccheInScadenza;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**Classe di configurazione della WebSocket in ambiente Spring Boot*/
@Configuration
@EnableWebSocket
public class WebSocketConfig {

	/*Costruisce e restituisce un webSocketEvasioneSaccaController
	 * @return webSocketEvasioneSaccaController
	 */
    @Bean
    public WebSocketEndPointEvasioneSacche webSocketEvasioneSaccaController() {
        return new WebSocketEndPointEvasioneSacche();
    }

    /*Costruisce e restituisce un webSocketSaccaInScadenzaController
	 * @return webSocketSaccaInScadenzaController
	 */
    @Bean
    public WebSocketEndPointSaccheInScadenza webSocketSaccaInScadenzaController() {
        return new WebSocketEndPointSaccheInScadenza();
    }

    /*Costruisce e restituisce un serverEndpointEvasioneExporter
	 * @return serverEndpointEvasioneExporter
	 */
    @Bean
    public ServerEndpointExporter serverEndpointEvasioneExporter() {
        return new ServerEndpointExporter();
    }
}