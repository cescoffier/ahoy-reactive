package me.escoffier.pirate.vertx;

import io.quarkus.runtime.StartupEvent;
import io.vertx.core.Vertx;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class VertxApp {

    @Inject
    Vertx vertx;

    public void onStart(@Observes StartupEvent ev) {
        int port = 8082;

        vertx.createHttpServer()
                .requestHandler(req -> req.response().end("Ahoy Matey! " + Thread.currentThread().getName()))
                .listen(port);
    }
}
