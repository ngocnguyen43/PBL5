package utils.encoder;

import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;
import jakarta.ws.rs.Encoded;

import java.lang.annotation.Annotation;

public class MessageEncoder implements Encoder.Text<String> {

    @Override
    public String encode(String s) throws EncodeException {
        return "";
    }

    @Override
    public void init(EndpointConfig config) {
        Text.super.init(config);
    }

    @Override
    public void destroy() {
        Text.super.destroy();
    }
}
