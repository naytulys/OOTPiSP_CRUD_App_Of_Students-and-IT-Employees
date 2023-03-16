package com.phones.phones;

import com.phones.annotations.LocalizedName;

@LocalizedName("Transmitter")
public class Transmitter extends AbstractCommunicator {
    private String transmitRange;
    private String waveRange;

    @LocalizedName("Transmit range")
    public String getTransmitRange() {
        return transmitRange;
    }

    public void setTransmitRange(String transmitRange) {
        this.transmitRange = transmitRange;
    }

    @LocalizedName("Wave range")
    public String getWaveRange() {
        return waveRange;
    }

    public void setWaveRange(String waveRange) {
        this.waveRange = waveRange;
    }
}
