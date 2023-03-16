package com.phones.phones.mobilePhones;

import com.phones.annotations.LocalizedName;
import com.phones.phones.MobilePhone;

@LocalizedName("Push-button telephone")
public class CellPhone extends MobilePhone {
    private boolean isFlashlight;
    private String buttonsCount;

    @LocalizedName("with flashlight")
    public boolean isFlashlight() {
        return isFlashlight;
    }

    public void setFlashlight(boolean flashlight) {
        isFlashlight = flashlight;
    }

    @LocalizedName("Buttons count")
    public String getButtonsCount() {
        return buttonsCount;
    }

    public void setButtonsCount(String buttonsCount) {
        this.buttonsCount = buttonsCount;
    }
}
