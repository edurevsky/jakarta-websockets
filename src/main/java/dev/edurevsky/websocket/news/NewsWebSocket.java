package dev.edurevsky.websocket.news;

import jakarta.inject.Inject;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint(
        value = "/news",
        encoders = {NewsEncoder.class}
)
public class NewsWebSocket {

    @Inject
    private NewsService newsService;

    @OnOpen
    public void onOpen(Session session) {
        this.newsService.connectReader(session);
    }

    @OnClose
    public void onClose(Session session) {
        this.newsService.disconnectReader(session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        this.newsService.disconnectReader(session);
        throwable.printStackTrace();
    }
}
