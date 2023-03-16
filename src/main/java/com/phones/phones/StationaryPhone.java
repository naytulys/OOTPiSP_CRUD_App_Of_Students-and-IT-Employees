package com.phones.phones;

import com.phones.annotations.LocalizedName;

public abstract class StationaryPhone extends AbstractCommunicator {

    private String number;

    @LocalizedName("Telephone number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
