package com.phones.phones.stationaryPhones;

import com.phones.annotations.LocalizedName;
import com.phones.phones.StationaryPhone;

@LocalizedName("Radio phone")
public class RadioPhone extends StationaryPhone {
    private String waveRange;
    private String signalRange;

    @LocalizedName("Wave range")
    public String getWaveRange() {
        return waveRange;
    }

    public void setWaveRange(String waveRange) {
        this.waveRange = waveRange;
    }

    @LocalizedName("Signal range")
    public String getSignalRange() {
        return signalRange;
    }

    public void setSignalRange(String signalRange) {
        this.signalRange = signalRange;
    }
}
