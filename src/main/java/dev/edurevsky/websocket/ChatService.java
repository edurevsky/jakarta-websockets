package dev.edurevsky.websocket;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Session;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class ChatService {

    private Map<Session, String> sessions;

    @PostConstruct
    void init() {
        System.out.printf("%s init%n".formatted(ChatService.class.getName()));
        this.sessions = new HashMap<>();
    }

    public void addChatter(Session session, String username) {
        this.sessions.put(session, username);
    }

    public void removeChatter(Session session) {
        this.sessions.remove(session);
    }

    public void broadcastMessage(Session session, Message message) {
        var username = this.sessions.get(session);

        message.setUsername(username);

        sessions.keySet().forEach(s -> {
            try {
                s.getBasicRemote().sendObject(message);
            } catch (IOException | EncodeException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
