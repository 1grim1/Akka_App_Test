package ru.grim;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import scala.Int;

import java.util.ArrayList;

public class Test {
    public static final String TEST_NAME = "testName";
    public static final String PARAMS = "params";
    public static final String EXPECTING_RESULT = "expectingResult";
    public static final String RESULT = "result";

    @JsonProperty(TEST_NAME)
    private String testName;

    @JsonProperty(PARAMS)
    private ArrayList<Integer> params;

    @JsonProperty(EXPECTING_RESULT)
    private String expectingResult;

    @JsonProperty(RESULT)
    private boolean result;

    @JsonCreator
    public Test(
            @JsonProperty(TEST_NAME) String testName,
            @JsonProperty(PARAMS) ArrayList<Integer> params,
            @JsonProperty(EXPECTING_RESULT) String expectingResult
    )
    {
     this.testName = testName;
     this.expectingResult = expectingResult;
     this.params = params;
    }

    public Test(String testName, ArrayList<Integer> params, String expectingResult, boolean result){
        this.result = result;
        this.testName = testName;
        this.expectingResult = expectingResult;
        this.params = params;
    }

    //getters

    public String getTestName(){
        return this.testName;
    }

    public String getExpectingResult(){
        return this.expectingResult;
    }

    public ArrayList<Integer> getParams(){
        return this.params;
    }

    public boolean getResult(){
        return  this.result;
    }

}
