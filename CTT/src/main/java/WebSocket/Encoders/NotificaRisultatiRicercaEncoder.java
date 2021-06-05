package WebSocket.Encoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaRisultatiRicerca;
import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class NotificaRisultatiRicercaEncoder implements Encoder.Text<NotificaRisultatiRicerca> {
    @Override
    public String encode(NotificaRisultatiRicerca object) throws EncodeException {
        try {
            CttDataBaseRestApplication.logger.error("WebSocket: sto serializzando la notifica risultati ricerca");
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            CttDataBaseRestApplication.logger.error("WebSocket: impossibile serializzare NotificaRisultatiRicerca");
        }
        return null;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
