package WebSocket.Decoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisannio.ingegneriaDelSoftware.CcsRestApplication;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.CTTName;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**Decoder utilizzato dalla webSocket per ricevere in maniera corretta oggetti CTTName*/
public class CTTNameDecoder implements Decoder.Text<CTTName> {

    @Override
    public CTTName decode(String s) throws DecodeException {
        try {
            return new ObjectMapper().readValue(s,CTTName.class);
        } catch (JsonProcessingException e) {
            CcsRestApplication.logger.error("Non sono riuscito a deserializzare l'oggetto "+s);
        }
        return null;
    }

    @Override
    public boolean willDecode(String s) {
        try {
            new ObjectMapper().readValue(s,CTTName.class);
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