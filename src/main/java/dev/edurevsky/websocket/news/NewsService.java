package dev.edurevsky.websocket.news;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.websocket.Session;

import java.time.format.DateTimeFormatter;
import java.util.*;

@ApplicationScoped
public class NewsService {

    private final Map<UUID, News> newsList = new HashMap<>();
    private final List<Session> readers = new ArrayList<>();

    public void connectReader(Session session) {
        this.readers.add(session);
    }

    public void disconnectReader(Session session) {
        this.readers.remove(session);
    }

    public void publish(News news) {
        this.newsList.put(news.getId(), news);

        var newsResponse = newsToNewsResponse(news);

        if (readers.isEmpty()) {
            return;
        }

        for (Session reader : readers) {
            reader.getAsyncRemote().sendObject(newsResponse);
        }
    }

    public NewsResponse getById(UUID id) {
        var news = this.newsList.get(id);
        if (news == null) {
            throw new RuntimeException("News not found");
        }

        return newsToNewsResponse(news);
    }

    public List<NewsResponse> findAll() {
        return newsList.values().stream().map(this::newsToNewsResponse).toList();
    }

    private NewsResponse newsToNewsResponse(News news) {
        return new NewsResponse(
                news.getTitle(),
                news.getAuthor(),
                news.getContent(),
                news.getId().toString(),
                news.getCreationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
        );
    }
}
