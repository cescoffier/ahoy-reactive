package me.escoffier.pirate;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import me.escoffier.pirate.hibernate.Pirate;
import org.jboss.resteasy.annotations.SseElementType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Duration;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class AhoyResource {

    @GET
    @Path("/ahoy")
    public String ahoy() {
        return "Aaaarrrrgggghhhh! " + Thread.currentThread().getName();
    }


    @GET
    @Path("/pirate")
    public Uni<Pirate> getPirate() {
        return Pirate.getRandomPirate();
    }

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType(MediaType.APPLICATION_JSON)
    public Multi<Pirate> stream() {
        Multi<Long> ticks = Multi.createFrom().ticks()
            .every(Duration.ofSeconds(1));
        return ticks
                    .onItem().transformToUniAndMerge(x -> getPirate());
    }
}
