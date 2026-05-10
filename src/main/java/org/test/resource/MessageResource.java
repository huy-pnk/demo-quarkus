package org.test.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.test.messaging.MessageProducer;

import java.util.Map;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

    @Inject
    MessageProducer producer;

    @POST
    public Response send(Map<String, String> body) {
        String content = body.get("content");
        if (content == null || content.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "content is required"))
                    .build();
        }
        producer.send("products", content);
        return Response.accepted(Map.of("status", "published", "content", content)).build();
    }
}
