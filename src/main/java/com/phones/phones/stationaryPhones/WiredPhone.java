package com.phones.phones.stationaryPhones;

import com.phones.annotations.LocalizedName;
import com.phones.phones.StationaryPhone;

@LocalizedName("Wired phone")
public class WiredPhone extends StationaryPhone {
    private String CableLength;
    private boolean isCableConnected;

    @LocalizedName("Cable length")
    public String getCableLength() {
        return CableLength;
    }

    public void setCableLength(String cableLength) {
        CableLength = cableLength;
    }

    @LocalizedName("Cable connected")
    public boolean isCableConnected() {
        return isCableConnected;
    }

    public void setCableConnected(boolean cableConnected) {
        isCableConnected = cableConnected;
    }
}
