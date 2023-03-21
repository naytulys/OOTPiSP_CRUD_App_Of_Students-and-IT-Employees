package com.entities;

import com.annotations.LocalizedName;

@LocalizedName("Programmer")
public class Programmer extends Employee {
    public enum Category {
        Junior,
        Middle,
        Senior;
    }

    public enum ProgSkills {
        C,
        JAVA,
        SWIFT;
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

}
