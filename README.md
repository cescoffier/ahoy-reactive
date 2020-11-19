# ahoy-reactive demo!

This repository is a demo of some reactive features integrated in Quarkus.

Associated slides are available on https://bit.ly/quarkus-reactive-ahoy-tt.

## Architecture
The demo is composed by:

* a PostGreSQL database pre-populated with _pirates_
* a Quarkus application containing a gRPC service (implementation and client), a Hibernate Reactive with Panache entity, a Spring controller, a JAX-RS resource, some reactive routes...

## Run
You need four terminals to run the application and at least one other terminal for the HTTP interactions.

### Infrastructure

From the root directory, run:

```
docker-compose up -d
```

### Application

```
mvn quarkus:dev
```

### Endpoints:
HTTP API exposed on port 8080:

- `http :8080/ahoy` -> regular hello
- `http :8080/pirate` -> Uni response
- `http :8080/stream` -> SSE response
- `http :8080/spring/ahoy` -> Spring hello
- `http :8080/aye` -> Route registered on the router
- `http :8080/rr/carouser` -> Reactive Routes
- `http :8080/rr/chantey` -> Reactive Routes returning Uni
- `http :8080/rr/chanteys` -> Reactive Routes returning Multi

HTTP API exposed on port 8082:

- `http :8082` -> Vert.x server
