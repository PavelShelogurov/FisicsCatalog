package com.example.phisicscatalog.ui.oprtic;

public class TestClassForCheckListView {

    private String name;
    private String secondName;

    public TestClassForCheckListView(String name, String secondName) {
        this.name = name;
        this.secondName = secondName;
    }


    @Override
    public String toString() {
        return "name='" + name + "'" +
                ", secondName='" + secondName + "'" ;
    }


}
