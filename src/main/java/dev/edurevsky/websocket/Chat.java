package dev.edurevsky.websocket;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint(
        value = "/chat/{username}",
        encoders = {MessageEncoder.class},
        decoders = {MessageDecoder.class}
)
public class Chat {

    @Inject
    private ChatService chatService;

    @PostConstruct
    void init() {
        System.out.printf("%s init%n", Chat.class.getName());
    }

    @OnMessage
    public void onMessage(Session session, Message message) {
        this.chatService.broadcastUserMessage(session, message);
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        this.chatService.addChatter(session, username);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println(closeReason);
        this.chatService.removeChatter(session);
    }
}
