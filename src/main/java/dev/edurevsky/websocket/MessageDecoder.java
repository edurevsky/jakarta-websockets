package dev.edurevsky.websocket;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;

import java.io.StringReader;

public class MessageDecoder implements Decoder.Text<Message> {

    @Override
    public Message decode(String s) throws DecodeException {
        JsonObject jsonObject = Json.createReader(new StringReader(s))
                .readObject();

        var content = jsonObject.getString("content");

        var message = new Message();
        message.setContent(content);

        return message;
    }

    @Override
    public boolean willDecode(String s) {
        try {
            Json.createReader(new StringReader(s)).readObject();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
