package WebSocket.Decoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche.NotificaRisultatiRicerca;
import it.unisannio.ingegneriaDelSoftware.CttRestApplication;


import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.util.ArrayList;

public class NotificaRisultatiRicercaDecoder implements Decoder.Text<NotificaRisultatiRicerca> {
    @Override
    public NotificaRisultatiRicerca decode(String s) throws DecodeException {
        try {
            return new ObjectMapper().readValue(s,NotificaRisultatiRicerca.class);
        } catch (JsonProcessingException e) {
            CttRestApplication.logger.error("WebSocket: impossibile deserializzare NotificaRisultatiRicerca");
            return null;
        }
    }

    @Override
    public boolean willDecode(String s) {
        try {
            new ObjectMapper().readValue(s,ArrayList.class);
            return true;
        } catch (JsonProcessingException e) {
            CttRestApplication.logger.error("WebSocket: impossibile deserializzare NotificaRisultatiRicerca");
            return false;
        }
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
