package WebSocket.Encoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisannio.ingegneriaDelSoftware.CcsRestApplication;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.CTTName;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**Encoder usato per la webSocket, in modo tale che riesca ad inoltrare in maniera corretta oggetti CTTName*/
public class CTTNameEncoder implements  Encoder.Text<CTTName> {

    @Override
    public String encode(CTTName object) throws EncodeException {
        try {
            CcsRestApplication.logger.error("Encoding in corso di "+object);
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            CcsRestApplication.logger.error("Non sono riuscito a fare l'encoding del CTT name: "+object);
            e.printStackTrace();
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