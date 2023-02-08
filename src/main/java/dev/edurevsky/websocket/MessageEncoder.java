package dev.edurevsky.websocket;

import jakarta.json.Json;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;

public class MessageEncoder implements Encoder.Text<Message> {

    @Override
    public String encode(Message message) throws EncodeException {
        return Json.createObjectBuilder()
                .add("username", message.getUsername())
                .add("content", message.getContent())
                .build()
                .toString();
    }
}
