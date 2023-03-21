package com.entities;

import com.annotations.LocalizedName;

@LocalizedName("Employee")
public class Employee extends Person {
    public enum Education {
        None,
        Middle,
        Height;
    }

    private double salary;
    private int experience;
    private Education education;

    @LocalizedName("Salary")
    public double getSalary() {
        return salary;
    }

    @LocalizedName("Experience")
    public int getExperience() {
        return experience;
    }

    @LocalizedName("Education")
    public Education getEducation() {
        return education;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public Employee(String name, String surname, Sex sex, String email, double salary, int experience, Education education) {
        super(name, surname, sex, email);
        this.salary = salary;
        this.experience = experience;
        this.education = education;
    }
}
