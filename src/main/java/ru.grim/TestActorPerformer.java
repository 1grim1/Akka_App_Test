package ru.grim;

import akka.actor.AbstractActor;
import akka.actor.ActorSelection;
import akka.japi.pf.ReceiveBuilder;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;

public class TestActorPerformer extends AbstractActor {
    private static final String SCRIPT_NAME = "nashorn";
    private static final String SCRIPT_PATH = "/user/storeActor";
    private ActorSelection actorSelection = getContext().actorSelection(SCRIPT_PATH);

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestMessage.class, testMessage -> {
                    actorSelection.tell(new StoreMessage(testMessage.getPackageId(),
                            runTest(
                                    testMessage.getJsScript(),
                                    testMessage.getFunctionName(),
                                    testMessage.getTest().getTestName(),
                                    testMessage.getTest().getExpectedResult(),
                                    testMessage.getTest().getParams())),
                            self());
                })
                .build();
    }

    public ArrayList<Test> runTest(
            String jsScript,
            String functionName,
            String testName,
            String expectingResult,
            ArrayList<Integer> params
    ) throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(SCRIPT_NAME);
        engine.eval(jsScript);
        Invocable invocable = (Invocable) engine;
        String result = invocable.invokeFunction(functionName, params.toArray()).toString();

        Test test = new Test(testName, expectingResult,params, expectingResult.equals(result));
        ArrayList<Test> currentTest = new ArrayList<Test>();
        currentTest.add(test);
        return currentTest;
    }
}
