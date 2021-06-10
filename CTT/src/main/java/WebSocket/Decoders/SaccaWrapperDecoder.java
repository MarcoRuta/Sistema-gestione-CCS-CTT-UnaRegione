package WebSocket.Decoders;

import com.google.gson.Gson;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Wrapper.SaccaWrapper;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class SaccaWrapperDecoder implements Decoder.Text<SaccaWrapper> {
    @Override
    public SaccaWrapper decode(String s) throws DecodeException {
        return new Gson().fromJson(s,SaccaWrapper.class);
    }

    @Override
    public boolean willDecode(String s) {
        if(new Gson().fromJson(s,SaccaWrapper.class) != null)
            return true;
        return false;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}