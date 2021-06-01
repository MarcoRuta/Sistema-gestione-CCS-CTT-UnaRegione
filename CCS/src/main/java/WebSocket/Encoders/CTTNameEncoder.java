package WebSocket.Encoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisannio.ingegneriaDelSoftware.CcsDataBaseRestApplication;
import it.unisannio.ingegneriaDelSoftware.Classes.CTTName;


import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class CTTNameEncoder implements  Encoder.Text<CTTName> {

    @Override
    public String encode(CTTName object) throws EncodeException {
        try {
            CcsDataBaseRestApplication.logger.error("Encoding in corso di "+object);
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            CcsDataBaseRestApplication.logger.error("Non sono riuscito a fare l'encoding del CTT name: "+object);
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
