package dev.edurevsky.websocket.news;

import jakarta.json.Json;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;

public class NewsEncoder implements Encoder.Text<NewsResponse> {

    @Override
    public String encode(NewsResponse newsResponse) throws EncodeException {
        return Json.createObjectBuilder()
                .add("author", newsResponse.author())
                .add("content", newsResponse.content())
                .add("id", newsResponse.id())
                .add("title", newsResponse.title())
                .add("creationDate", newsResponse.creationDate())
                .build()
                .toString();
    }
}
