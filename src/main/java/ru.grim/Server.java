package ru.grim;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.pattern.PatternsCS;
import akka.routing.RoundRobinPool;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import akka.testkit.TestActor;

import java.io.IOException;
import java.util.regex.Pattern;
import java.util.concurrent.CompletionStage;

import static akka.http.javadsl.server.Directives.completeOKWithFuture;
import static akka.http.javadsl.server.Directives. *;

public class Server {

    private ActorRef storeActor;
    private ActorRef actorPackageTest;
    private ActorRef performerActor;

    public static final String SERVER_NAME = "localhost";
    public static final int PORT = 2077;
    public static final int POOL_NUM = 6;
    public static final String ACTOR_PACKAGE_TEST = "actorPackageTest";
    public static final String ACTOR_PERFORMER = "actorPerformer";
    public static final String STORE_ACTOR = "storeActor";
    public static final int TIME_OUT = 5000;

    private Server(final ActorSystem actorSystem){
        storeActor = actorSystem.actorOf(Props.create(StoreActor.class), STORE_ACTOR);
        actorPackageTest = actorSystem.actorOf(Props.create(ActorPackageTest.class), ACTOR_PACKAGE_TEST);
        performerActor = actorSystem.actorOf(new RoundRobinPool(POOL_NUM).props(Props.create(TestActorPerformer.class)), ACTOR_PERFORMER);
    }

    public static void main(String[] args) throws IOException {
        ActorSystem actorSystem = ActorSystem.create("Actor_System");
        final Http http = Http.get(actorSystem);
        final ActorMaterializer actorMaterializer = ActorMaterializer.create(actorSystem);

        Server server = new Server(actorSystem);

        final Flow<HttpRequest, HttpResponse, NotUsed> flow =
                server.createRoute().flow(actorSystem, actorMaterializer);

        final CompletionStage<ServerBinding> serverBindingCompletionStage = http.bindAndHandle(
          flow,
          ConnectHttp.toHost(SERVER_NAME, PORT),
          actorMaterializer
        );
        System.out.println("Starting server!");
        System.in.read();
        serverBindingCompletionStage
                .thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> actorSystem.terminate());
        System.out.println("End");
    }

    private Route createRoute(){
        return route(
                get(() ->
                        parameter("packageID", (packageID) ->{
                            CompletionStage<Object> result = PatternsCS.ask(
                                    storeActor,
                                    new Message(Integer.parseInt(packageID)),
                                    TIME_OUT);//5000
                            return completeOKWithFuture(result, Jackson.marshaller());
                        })),
                        post(() -> entity(Jackson.unmarshaller(TestMessage.class), message -> {
                            actorPackageTest.tell(message, ActorRef.noSender());
                            return complete("Started test\n");
                        }))
                );
    }

}
