package ru.grim;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.http.javadsl.server.Route;
import akka.pattern.PatternsCS;
import akka.routing.RoundRobinPool;
import akka.stream.ActorMaterializer;

import java.util.regex.Pattern;
import java.util.concurrent.CompletionStage;

public class Server {

    private ActorRef storeActor;
    private ActorRef actorPackageTest;
    private ActorRef performerActor;

    public static final String SERVER_NAME = "localhost";
    public static final int PORT = 2077;
    public static final int POOL_NUM = 6;
    public static final String ACTOR_PACKAGE_TEST = "actorPackageTest";
    public static final String ACTOR_PERFORMER = "actorPerformer";

    private Server(final ActorSystem actorSystem){
        storeActor = actorSystem.actorOf(Props.create(StoreActor.class));
        actorPackageTest = actorSystem.actorOf(Props.create(ActorPackageTest.class), ACTOR_PACKAGE_TEST);
        performerActor =  actorSystem.actorOf(new RoundRobinPool(POOL_NUM).props(Props.create(ActorPerformer.class), ACTOR_PERFORMER));
    }

    public static void main(String[] args){
        ActorSystem actorSystem = ActorSystem.create("Actor_System");
        final Http http = Http.get(actorSystem);
        final ActorMaterializer actorMaterializer = ActorMaterializer.create(actorSystem);
    }

    private Route createRoute(){
        return route(
                get(() ->
                        param("packageID", (packageID) ->{
                            CompletionStage<Object> result = PatternsCS.ask(
                                    storeActor,

                            )
                        })
                        )
        );
    }

}
