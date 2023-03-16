package com.phones.phones.mobilePhones;

import com.phones.annotations.LocalizedName;
import com.phones.phones.MobilePhone;

@LocalizedName("Smartphone")
public class SmartPhone extends MobilePhone {
    private boolean fourGSupport;
    private boolean waterResistant;
    private boolean fingerUnlock;
    private boolean faceUnlock;
    private String operatingSystem;

    @LocalizedName("4G")
    public boolean isFourGSupport() {
        return fourGSupport;
    }

    @LocalizedName("Water resistant")
    public boolean isWaterResistant() {
        return waterResistant;
    }

    @LocalizedName("Finger Unlock")
    public boolean isFingerUnlock() {
        return fingerUnlock;
    }

    @LocalizedName("Face Unlock")
    public boolean isFaceUnlock() {
        return faceUnlock;
    }

    @LocalizedName("Operating system")
    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setFourGSupport(boolean fourGSupport) {
        this.fourGSupport = fourGSupport;
    }

    public void setWaterResistant(boolean waterResistant) {
        this.waterResistant = waterResistant;
    }

    public void setFingerUnlock(boolean fingerUnlock) {
        this.fingerUnlock = fingerUnlock;
    }

    public void setFaceUnlock(boolean faceUnlock) {
        this.faceUnlock = faceUnlock;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }
}
