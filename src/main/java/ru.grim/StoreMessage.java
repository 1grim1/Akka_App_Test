package ru.grim;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class StoreMessage {
    public static final String PACKAGE_ID = "packageID";
    public static final String TESTS_LIST = "testsList";

    @JsonProperty(PACKAGE_ID)
    private int packageID;

    @JsonProperty(TESTS_LIST)
    private ArrayList<Test> testsList;

    @JsonCreator
    public StoreMessage(
            @JsonProperty(PACKAGE_ID) int packageID,
            @JsonProperty(TESTS_LIST) ArrayList<Test> testsList
    )
    {
        this.packageID = packageID;
        this.testsList = testsList;
    }

    //getters

    public int getPackageID(){
        return this.packageID;
    }


    public ArrayList<Test> getTestsList(){
        return this.testsList;
    }


}
