package ru.grim;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.http.javadsl.server.Route;
import akka.pattern.PatternsCS;

import java.util.regex.Pattern;
import java.util.concurrent.CompletionStage;

public class Server {

    private ActorRef storeActor;

    public static final String SERVER_NAME = "localhost";
    public static final int PORT = 2077;


    private Server(final ActorSystem actorSystem){
        storeActor = actorSystem.actorOf(Props.create(StoreActor.class))
    }

    public static void main(String[] args){
        ActorSystem actorSystem = ActorSystem.create("Actor_System");
        Http http = Http.get(actorSystem);
    }

    private Route createRoute(){
        return route(
                get(() -> param("packageID", (packageID) -> {
                    CompletionStage<> result = PatternsCS.ask(

                    )
                }))
        )
    }

}
