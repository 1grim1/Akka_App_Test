package ru.grim;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TestMessage {

    public static final String PACKAGE_ID = "packageID";
    public static final String FUNCTION_NAME = "functionName";
    public static final String JS_SCRIPT = "jsScript";
    public static final String TESTS_ARRAY = "testsArray";

    @JsonProperty(PACKAGE_ID)
    private int packageID;

    @JsonProperty(FUNCTION_NAME)
    private String  functionName;

    @JsonProperty(JS_SCRIPT)
    private String jsScript;

    @JsonProperty(TESTS_ARRAY)
    private ArrayList<Test> testsList;

    @JsonCreator
    TestMessage(
            @JsonProperty(PACKAGE_ID) int packageID,
            @JsonProperty(FUNCTION_NAME) String functionName,
            @JsonProperty(JS_SCRIPT) String jsScript,
            @JsonProperty(TESTS_ARRAY) ArrayList<Test> testsArray)
    {
        this.packageID = packageID;
        this.testsList = testsArray;
        this.functionName = functionName;
        this.jsScript = jsScript;
    }

    //getters

    public int getPackageID(){
        return this.packageID;
    }

    public String getFunctionName(){
        return this.functionName;
    }

    public String getJsScript(){
        return this.jsScript;
    }

    public ArrayList<Test> getTestsArray(){
        return this.testsList;
    }

}
