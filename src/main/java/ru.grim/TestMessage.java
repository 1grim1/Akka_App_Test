package ru.grim;

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
    private int functionName;

    @JsonProperty(JS_SCRIPT)
    private int jsScript;

    @JsonProperty(TESTS_ARRAY)
    private ArrayList<Test> testsArray;

}
