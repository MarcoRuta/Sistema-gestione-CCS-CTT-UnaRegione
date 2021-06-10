package WebSocket.Encoders;

import com.google.gson.Gson;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Wrapper.SaccaWrapper;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class SaccaWrapperEncoder implements Encoder.Text<SaccaWrapper> {
    @Override
    public String encode(SaccaWrapper object) throws EncodeException {
        return new Gson().toJson(object);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}