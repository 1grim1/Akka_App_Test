package ru.grim;

import akka.actor.AbstractActor;
import akka.actor.ActorSelection;
import akka.japi.pf.ReceiveBuilder;

public class TestActorPerformer extends AbstractActor {
    private static final String SCRIPT_NAME = "/user/storeActor";
    private ActorSelection actorSelection = getContext().actorSelection(SCRIPT_NAME);

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestMessage.class, testMessage -> {
                    actorSelection.tell(new StoreMessage());
                })
                .build();
    }
}
