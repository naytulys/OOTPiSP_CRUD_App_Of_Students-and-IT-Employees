package com;


import com.entities.*;
import com.ui.MainWindow;
import com.utils.ClassDescription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Main {

    private static final ObservableList<ClassDescription> classList;

    static {
        /* united description for all app classes into one list */
        classList = FXCollections.observableArrayList();
        classList.addAll(
                new ClassDescription(Student.class),
                new ClassDescription(Programmer.class),
                new ClassDescription(Designer.class),
                new ClassDescription(Manager.class),
                new ClassDescription(Tester.class)
        );
    }

    public static ObservableList<ClassDescription> getClassList() {
        return classList;
    }

    public static void main(String[] args) {
        /* launch main window of this app */
        MainWindow.mainWindow(args);
    }

}
