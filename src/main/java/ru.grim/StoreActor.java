package ru.grim;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StoreActor extends AbstractActor {

    private Map<Integer, ArrayList<Test>> container = new HashMap<Integer, ArrayList<Test>>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder
                .create()
                .match(StoreMessage.class, message -> {
                    if (container.containsKey(message.getPackageId())){
                        ArrayList<Test> currentTestArrayList = container.get(message.getPackageId());
                        currentTestArrayList.addAll(message.getTestsList());
                        container.replace(message.getPackageId(), currentTestArrayList);
                    }
                    else {
                        container.put(message.getPackageId(), message.getTestsList());
                    }
                })
                .match(Message.class, request -> {
                    sender().tell(
                            new StoreMessage(request.getPackageID(), container.get(request.getPackageID())), self());
                })
                .build();
    }
}
