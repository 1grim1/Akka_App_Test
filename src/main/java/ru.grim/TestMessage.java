package ru.grim;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestMessage {


    private int packageId;

    private String  functionName;
    private String jsScript;
    private Test test;

    @JsonCreator
    TestMessage(int packageId, String functionName, String jsScript, Test test)
    {
        this.packageId = packageId;
        this.test = test;
        this.functionName = functionName;
        this.jsScript = jsScript;
    }

    //getters

    public int getPackageId(){
        return this.packageId;
    }

    public String getFunctionName(){
        return this.functionName;
    }

    public String getJsScript(){
        return this.jsScript;
    }

    public Test getTest(){
        return this.test;
    }

}
