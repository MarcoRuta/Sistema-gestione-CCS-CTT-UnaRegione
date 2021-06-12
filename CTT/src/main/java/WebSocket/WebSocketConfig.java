package WebSocket;

import it.unisannio.ingegneriaDelSoftware.Filtri.FiltroWebSocket;
import it.unisannio.ingegneriaDelSoftware.Magazziniere.EvasioneSacche.WebSocketEndPointEvasioneSacche;
import it.unisannio.ingegneriaDelSoftware.Magazziniere.SmaltimentoSacche.WebSocketEndPointSmaltimentoSacche;
import it.unisannio.ingegneriaDelSoftware.Operatore.RicercaGlobale.WebSocketEndPointResultRicercaGlobale;
import it.unisannio.ingegneriaDelSoftware.Operatore.SaccheInScadenza.WebSocketEndPointSaccheInScadenza;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/** classe di configurazione della WebSocket in ambiente Spring Boot
 * Vengono inizializzati diversi bean (relativi alle websocket) in modo da renderli fruibili alla SpingApplication a runTime*/
@Configuration
@EnableWebSocket
public class WebSocketConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filtroWebSocketCotroller());
        filterRegistrationBean.addUrlPatterns("/ws/*");
        return filterRegistrationBean;
    }

    @Bean
    public FiltroWebSocket filtroWebSocketCotroller(){
        return new FiltroWebSocket();
    }

    @Bean
    public WebSocketEndPointEvasioneSacche webSocketEvasioneSaccaController() {
        return new WebSocketEndPointEvasioneSacche();
    }

    @Bean
    public WebSocketEndPointSaccheInScadenza webSocketSaccaInScadenzaController() {
        return new WebSocketEndPointSaccheInScadenza();
    }

    @Bean
    public WebSocketEndPointSmaltimentoSacche webSocketSaccaSmaltimentoSaccheController() {
        return new WebSocketEndPointSmaltimentoSacche();
    }

    @Bean
    public WebSocketEndPointResultRicercaGlobale webSocketEndPointResultRicercaGlobaleController() {
        return new WebSocketEndPointResultRicercaGlobale();
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }





}
