package com.phones.entities;

import com.phones.annotations.LocalizedName;

@LocalizedName("Manager")
public class Manager extends Employee {
    private double bonus;

    @LocalizedName("Bonus")
    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
}
