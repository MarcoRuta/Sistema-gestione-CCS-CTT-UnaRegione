package WebSocket;

import WebSocket.ServerEndpoint.WebSocketEndpointSaccheInScadenza;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/*Classe di configurazione della WebSocket in ambiente Spring Boot */
@Configuration
@EnableWebSocket
public class WebSocketConfig {

	/*Costruisce e restituisce un WebSocketEndpointSaccheInScadenza
	 * @return WebSocketEndpointSaccheInScadenza
	 */
    @Bean
    public WebSocketEndpointSaccheInScadenza webSocketController() {
        return new WebSocketEndpointSaccheInScadenza();
    }

    /*Costruisce e restituisce un ServerEndpointExporter
	 * @return ServerEndpointExporter
	 */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}