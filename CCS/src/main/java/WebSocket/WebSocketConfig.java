package WebSocket;

import it.unisannio.ingegneriaDelSoftware.Filtri.FiltroWebSocket;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import it.unisannio.ingegneriaDelSoftware.SaccheInScadenza.WebSocketEndpointSaccheInScadenza;

/*Classe di configurazione della WebSocket in ambiente Spring Boot */
@Configuration
@EnableWebSocket
public class WebSocketConfig {

    @Bean
    public FilterRegistrationBean webSocketFilterRegistration() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filtroWebSocketController());
        filterRegistrationBean.addUrlPatterns("/ws/*");
        return filterRegistrationBean;
    }

    /**Costruisce e restituisce un FiltroWebSocket
     * @return FiltroWebSocket
     */
    @Bean
    public FiltroWebSocket filtroWebSocketController() {
        return new FiltroWebSocket();
    }

	/**Costruisce e restituisce un WebSocketEndpointSaccheInScadenza
	 * @return WebSocketEndpointSaccheInScadenza
	 */
    @Bean
    public WebSocketEndpointSaccheInScadenza webSocketController() {
        return new WebSocketEndpointSaccheInScadenza();
    }

    /**Costruisce e restituisce un ServerEndpointExporter
	 * @return ServerEndpointExporter
	 */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}