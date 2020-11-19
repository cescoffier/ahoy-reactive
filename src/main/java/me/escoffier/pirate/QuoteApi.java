package me.escoffier.pirate;

import io.quarkus.grpc.runtime.annotations.GrpcService;
import io.quarkus.vertx.web.ReactiveRoutes;
import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.RouteBase;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@RouteBase(path = "/rr")
public class QuoteApi {

    @Inject
    @GrpcService("quotes")
    MutinyQuoteServiceGrpc.MutinyQuoteServiceStub quotes;

    // GET /carouser - where's the booty?
    @Route(methods = HttpMethod.GET, path = "/carouser")
    public void handler(RoutingContext rc) {
        rc.response().end("Where's the booty? " + Thread.currentThread().getName());
    }

    // GET /chantey
    @Route(methods = HttpMethod.GET, path = "/chantey")
    public Uni<String> getQuote() {
        return quotes.getRandomQuote(QuoteRequest.newBuilder().build())
                .onItem().transform(qr -> qr.getQuote());
    }

    // GET /chanteys - sse
    @Route(methods = HttpMethod.GET, path = "/chanteys")
    public Multi<String> getQuotes() {
        return ReactiveRoutes.asJsonArray(Multi.createFrom().range(0, 3)
                .onItem().transformToUniAndConcatenate(i -> getQuote()));
    }

}
