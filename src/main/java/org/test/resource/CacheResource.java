package org.test.resource;

import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/cache")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class CacheResource {

    private final ValueCommands<String, String> valueCommands;

    @Inject
    public CacheResource(RedisDataSource redisDataSource) {
        this.valueCommands = redisDataSource.value(String.class);
    }

    @PUT
    @Path("/{key}")
    public Response put(@PathParam("key") String key, String value) {
        valueCommands.set(key, value);
        return Response.ok().build();
    }

    @GET
    @Path("/{key}")
    public Response get(@PathParam("key") String key) {
        String value = valueCommands.get(key);
        if (value == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(value).build();
    }
}
