package ru.grim;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Test {
    public static final String TEST_NAME = "testName";
    public static final String PARAMS = "params";
    public static final String EXPECTED_RESULT = "expectedResult";
    public static final String RESULT = "result";

    @JsonProperty(TEST_NAME)
    private String testName;

    @JsonProperty(PARAMS)
    private ArrayList<Integer> params;

    @JsonProperty(EXPECTED_RESULT)
    private String expectedResult;

    @JsonProperty(RESULT)
    private boolean result;

    @JsonCreator
    public Test(
            @JsonProperty(TEST_NAME) String testName,
            @JsonProperty(PARAMS) ArrayList<Integer> params,
            @JsonProperty(EXPECTED_RESULT) String expectedResult
    )
    {
     this.testName = testName;
     this.expectedResult = expectedResult;
     this.params = params;
     this.result = false;
    }

    public Test(String testName, ArrayList<Integer> params, String expectedResult, boolean result){
        this.result = result;
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
    }

    //getters

    public String getTestName(){
        return this.testName;
    }

    public String getExpectedResult(){
        return this.expectedResult;
    }

    public ArrayList<Integer> getParams(){
        return this.params;
    }

    public boolean getResult(){
        return  this.result;
    }

}
