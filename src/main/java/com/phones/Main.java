package com.phones;

import com.phones.phones.Transmitter;
import com.phones.phones.mobilePhones.CellPhone;
import com.phones.phones.mobilePhones.SmartPhone;
import com.phones.phones.stationaryPhones.RadioPhone;
import com.phones.phones.stationaryPhones.WiredPhone;
import com.phones.utils.ClassDescription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Main {

    private static ObservableList<ClassDescription> classList;

    public static ObservableList<ClassDescription> getClassList() {
        return classList;
    }

    public static void main(String[] args) {
        classList = FXCollections.observableArrayList();
        classList.addAll(
                new ClassDescription(SmartPhone.class),
                new ClassDescription(CellPhone.class),
                new ClassDescription(RadioPhone.class),
                new ClassDescription(WiredPhone.class),
                new ClassDescription(Transmitter.class)
        );
        // run mainWindow with args
    }

}
