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
        this.broadcastServerMessage("%s joined".formatted(username));
    }

    public void removeChatter(Session session) {
        var username = this.sessions.get(session);
        this.sessions.remove(session);
        this.broadcastServerMessage("%s left".formatted(username));
    }

    public void broadcastUserMessage(Session session, Message message) {
        var username = this.sessions.get(session);

        message.setUsername(username);

        this.broadcastMessage(message);
    }

    private void broadcastServerMessage(String content) {
        var message = new Message("Server", content);

        this.broadcastMessage(message);
    }

    private void broadcastMessage(Message message) {
        sessions.keySet().forEach(session -> {
            try {
                session.getBasicRemote().sendObject(message);
            } catch (IOException | EncodeException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
