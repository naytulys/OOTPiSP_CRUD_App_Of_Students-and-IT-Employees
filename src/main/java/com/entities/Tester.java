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

    public Tester(){

    }

    public Tester(String name, String surname, Sex sex, String email, double salary, int experience, Education education, TestType testType) {
        super(name, surname, sex, email, salary, experience, education);
        this.testType = testType;
    }

}
