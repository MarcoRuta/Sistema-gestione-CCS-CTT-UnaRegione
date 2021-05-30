package WebSocketConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/* classe di configurazione della WebSocket in ambiete Spring Boot */
@Configuration
@EnableWebSocket
public class WebSocketConfig{

    @Bean
    public WebSocketEndpoint webSocketController() {
        return new WebSocketEndpoint();
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

	

}
