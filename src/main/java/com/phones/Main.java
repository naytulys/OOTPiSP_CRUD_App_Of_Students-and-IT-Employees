package com.phones;

import com.phones.entities.*;
import com.phones.ui.MainWindow;
import com.phones.utils.ClassDescription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Main {

    private static ObservableList<ClassDescription> classList;

    public static ObservableList<ClassDescription> getClassList() {
        return classList;
    }

    public static void main(String[] args) {
        /* united description for all app classes into one list */
        classList = FXCollections.observableArrayList();
        classList.addAll(
                new ClassDescription(Student.class),
                new ClassDescription(Programmer.class),
                new ClassDescription(Designer.class),
                new ClassDescription(Manager.class),
                new ClassDescription(Tester.class)
        );
        /* launch main window of this app */
        MainWindow.mainWindow(args);
    }

}
