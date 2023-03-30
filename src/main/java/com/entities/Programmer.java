package com.entities;

import com.annotations.LocalizedName;

import java.io.Serializable;

@LocalizedName("Programmer")
public class Programmer extends Employee implements Serializable {
    public enum Category {
        Junior,
        Middle,
        Senior
    }

    public enum ProgSkills {
        C,
        JAVA,
        SWIFT
    }

    private Category category;
    private ProgSkills progSkills;

    @LocalizedName("Category")
    public Category getCategory() {
        return category;
    }

    @LocalizedName("Prog Skills")
    public ProgSkills getProgSkills() {
        return progSkills;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setProgSkills(ProgSkills progSkills) {
        this.progSkills = progSkills;
    }

    public Programmer(){

    }

    public Programmer(String name, String surname, Sex sex, String email, double salary, int experience, Education education, Category category, ProgSkills progSkills) {
        super(name, surname, sex, email, salary, experience, education);
        this.category = category;
        this.progSkills = progSkills;
    }
}
