package dev.edurevsky.websocket.news;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Path("/news")
public class NewsResource {

    @Inject
    private NewsService newsService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(News news) {
        this.newsService.publish(news);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNews() {
        var newsList = this.newsService.findAll();

        return Response.ok(newsList).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") UUID uuid) {
        var news = this.newsService.getById(uuid);

        return Response.ok(news).build();
    }
}
