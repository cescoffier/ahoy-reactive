package me.escoffier.pirate.router;

import io.vertx.ext.web.Router;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class RouterApi {

    public void init(@Observes Router router) {
        // filter - ship header
        router.get().handler(rc -> {
            rc.response().putHeader("X-Ship", getShip());
            rc.next();
        });

        // /aye -> Sail, Ho!
        router.get("/aye").handler(rc -> rc.response().end("Sail, Ho! " + Thread.currentThread().getName()));

    }

    // -------------------

    private static final List<String> SHIPS = Arrays.asList("clipper", "cog", "galley");

    private final Random random = new Random();

    public String getShip() {
        return SHIPS.get(random.nextInt(SHIPS.size()));
    }
}
