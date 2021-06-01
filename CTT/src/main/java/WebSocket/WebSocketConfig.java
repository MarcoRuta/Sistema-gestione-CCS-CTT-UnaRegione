package WebSocket;

import WebSocket.ServerEndpoint.WebSocketEndPointEvasioneSacche;
import WebSocket.ServerEndpoint.WebSocketEndPointSaccheInScadenza;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/* classe di configurazione della WebSocket in ambiete Spring Boot */
@Configuration
@EnableWebSocket
public class WebSocketConfig {

    @Bean
    public WebSocketEndPointEvasioneSacche webSocketEvasioneSaccaController() {
        return new WebSocketEndPointEvasioneSacche();
    }

    @Bean
    public WebSocketEndPointSaccheInScadenza webSocketSaccaInScadenzaController() {
        return new WebSocketEndPointSaccheInScadenza();
    }

    @Bean
    public ServerEndpointExporter serverEndpointEvasioneExporter() {
        return new ServerEndpointExporter();
    }





}
