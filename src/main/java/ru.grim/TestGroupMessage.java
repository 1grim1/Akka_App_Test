package ru.grim;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class TestGroupMessage {
    public static final String PACKAGE_ID = "packageId";
    public static final String FUNCTION_NAME = "functionName";
    public static final String JS_SCRIPT = "jsScript";
    public static final String TESTS = "tests";

    @JsonProperty(PACKAGE_ID)
    private int packageId;

    @JsonProperty(FUNCTION_NAME)
    private String  functionName;

    @JsonProperty(JS_SCRIPT)
    private String jsScript;

    @JsonProperty(TESTS)
    private ArrayList<Test> tests;

    @JsonCreator
    TestGroupMessage(
            @JsonProperty(PACKAGE_ID) int packageId,
            @JsonProperty(FUNCTION_NAME) String functionName,
            @JsonProperty(JS_SCRIPT) String jsScript,
            @JsonProperty(TESTS) ArrayList<Test> tests)
    {
        this.packageId = packageId;
        this.tests = tests;
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

    public ArrayList<Test> getTests(){
        return this.tests;
    }

}
