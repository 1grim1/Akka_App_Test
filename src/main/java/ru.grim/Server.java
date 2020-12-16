package ru.grim;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.Route;
import akka.pattern.PatternsCS;
import akka.routing.RoundRobinPool;
import akka.stream.ActorMaterializer;
import akka.testkit.TestActor;

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
    public static final int TIME_OUT = 5000;

    private Server(final ActorSystem actorSystem){
        storeActor = actorSystem.actorOf(Props.create(StoreActor.class));
        actorPackageTest = actorSystem.actorOf(Props.create(ActorPackageTest.class), ACTOR_PACKAGE_TEST);
        performerActor =  actorSystem.actorOf(new RoundRobinPool(POOL_NUM).props(Props.create(TestActorPerformer.class), ACTOR_PERFORMER));
    }

    public static void main(String[] args){
        ActorSystem actorSystem = ActorSystem.create("Actor_System");
        final Http http = Http.get(actorSystem);
        final ActorMaterializer actorMaterializer = ActorMaterializer.create(actorSystem);
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
