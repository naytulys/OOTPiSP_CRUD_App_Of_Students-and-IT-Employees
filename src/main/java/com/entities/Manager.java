package com.entities;

import com.annotations.LocalizedName;

import java.io.Serializable;

@LocalizedName("Manager")
public class Manager extends Employee implements Serializable {
    private double bonus;

    @LocalizedName("Bonus")
    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public Manager(){

    }

    public Manager(String name, String surname, Sex sex, String email, double salary, int experience, Education education, double bonus) {
        super(name, surname, sex, email, salary, experience, education);
        this.bonus = bonus;
    }
}
