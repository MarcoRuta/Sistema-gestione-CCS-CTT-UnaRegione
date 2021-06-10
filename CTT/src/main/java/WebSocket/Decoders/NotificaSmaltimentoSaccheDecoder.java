package WebSocket.Decoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche.NotificaSmaltimentoSacche;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class NotificaSmaltimentoSaccheDecoder implements Decoder.Text<NotificaSmaltimentoSacche> {
    @Override
    public NotificaSmaltimentoSacche decode(String s) throws DecodeException {
        try {
            return new ObjectMapper().readValue(s,NotificaSmaltimentoSacche.class);
        } catch (JsonProcessingException e) {
            //do nothing
        }
        return null;
    }

    @Override
    public boolean willDecode(String s) {
        try {
             new ObjectMapper().readValue(s,NotificaSmaltimentoSacche.class);
             return true;
        } catch (JsonProcessingException e) {
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
