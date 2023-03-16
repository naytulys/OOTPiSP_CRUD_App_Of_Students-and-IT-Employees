package com.phones.phones;

import com.phones.annotations.LocalizedName;

public abstract class MobilePhone extends StationaryPhone {
    private int simSlots;

    @LocalizedName("Sim cards slots count")
    public int getSimSlots() {
        return simSlots;
    }

    public void setSimSlots(int simSlots) {
        this.simSlots = simSlots;
    }

}
