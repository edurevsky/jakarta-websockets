package dev.edurevsky.websocket.news;

public record NewsResponse(String title, String author, String content, String id, String creationDate) {
}
