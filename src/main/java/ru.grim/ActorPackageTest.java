package ru.grim;

import akka.actor.AbstractActor;
import akka.actor.ActorSelection;
import akka.japi.pf.ReceiveBuilder;

public class ActorPackageTest extends AbstractActor {
    private final String ACTOR_PERFORMER_PATH = "/user/testActorPerformer";

    private ActorSelection actorSelection = getContext().actorSelection(ACTOR_PERFORMER_PATH);

    @Override
    public Receive createReceive() {
        return ReceiveBuilder
                .create()
                .match(TestGroupMessage.class, testGroupMessage -> {
                    for (Test test : testGroupMessage.getTestsList()){
                        actorSelection.tell(new TestMessage(
                                testGroupMessage.getPackageID(),
                                testGroupMessage.getFunctionName(),
                                testGroupMessage.getJsScript(),
                                test),
                                self());
                    }
                })
                .build();
    }
}
