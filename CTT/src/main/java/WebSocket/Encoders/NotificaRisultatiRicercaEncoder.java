package WebSocket.Encoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche.NotificaRisultatiRicerca;
import it.unisannio.ingegneriaDelSoftware.CttRestApplication;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class NotificaRisultatiRicercaEncoder implements Encoder.Text<NotificaRisultatiRicerca> {
    @Override
    public String encode(NotificaRisultatiRicerca object) throws EncodeException {
        try {
            CttRestApplication.logger.error("WebSocket: sto serializzando la notifica risultati ricerca");
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            CttRestApplication.logger.error("WebSocket: impossibile serializzare NotificaRisultatiRicerca");
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
