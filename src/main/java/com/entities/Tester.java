package com.entities;

import com.annotations.LocalizedName;

@LocalizedName("Tester")
public class Tester extends Employee {
    public enum TestType {
        Manual,
        Automated;
    }
    private TestType testType;

    @LocalizedName("Test Type")
    public TestType getTestType() {
        return testType;
    }

    public void setTestType(TestType testType) {
        this.testType = testType;
    }
}
