package dev.edurevsky.websocket;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat/{username}")
public class Chat {

    @Inject
    private ChatService chatService;

    @PostConstruct
    void init() {
        System.out.printf("%s init%n", Chat.class.getName());
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        this.chatService.broadcastMessage(session, message);
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        this.chatService.addChatter(session, username);
    }

    @OnClose
    public void onClose(Session session) {
        this.chatService.removeChatter(session);
    }
}
