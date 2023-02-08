package dev.edurevsky.websocket;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/ping")
public class Ping {

    @OnOpen
    public void onOpen(Session session) {
        System.out.printf("Session with id %s opened%n", session.getId());
    }

    @OnMessage
    public void onMessage(Session session, String ignoredMessage) {
        System.out.printf("Received message from %s%n", session.getId());
    }

    @OnClose
    public void onClose(Session session) {
        System.out.printf("Session with id %s closed%n", session.getId());
    }
}
