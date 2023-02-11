package dev.edurevsky.websocket.news;

import java.time.LocalDateTime;
import java.util.UUID;

public class News {

    private final UUID id;
    private String title;
    private String author;
    private String content;
    private final LocalDateTime creationDate;

    public News() {
        this.id = UUID.randomUUID();
        this.creationDate = LocalDateTime.now();
    }

    public News(String title, String author, String content) {
        this();
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}
