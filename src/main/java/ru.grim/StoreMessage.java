package ru.grim;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class StoreMessage {
    public static final String PACKAGE_ID = "packageId";
    public static final String TESTS = "tests";

    @JsonProperty(PACKAGE_ID)
    private int packageId;

    @JsonProperty(TESTS)
    private ArrayList<Test> testsList;

    @JsonCreator
    public StoreMessage(
            @JsonProperty(PACKAGE_ID) int packageId,
            @JsonProperty(TESTS) ArrayList<Test> testsList
    )
    {
        this.packageId = packageId;
        this.testsList = testsList;
    }

    //getters

    public int getPackageId(){
        return this.packageId;
    }


    public ArrayList<Test> getTestsList(){
        return this.testsList;
    }


}
