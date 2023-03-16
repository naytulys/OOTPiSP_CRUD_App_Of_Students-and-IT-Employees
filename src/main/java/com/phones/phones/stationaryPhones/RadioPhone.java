package com.phones.phones.stationaryPhones;

import com.phones.phones.StationaryPhone;

public class RadioPhone extends StationaryPhone {
    private String waveRange;
    private String signalRange;

    public String getWaveRange() {
        return waveRange;
    }

    public void setWaveRange(String waveRange) {
        this.waveRange = waveRange;
    }

    public String getSignalRange() {
        return signalRange;
    }

    public void setSignalRange(String signalRange) {
        this.signalRange = signalRange;
    }
}
