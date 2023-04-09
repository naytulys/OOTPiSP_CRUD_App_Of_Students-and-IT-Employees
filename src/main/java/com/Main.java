package com;


import com.entities.*;
import com.serializers.BinarySerializer;
import com.serializers.SerializerDescription;
import com.serializers.TextSerializer;
import com.serializers.XMLSerializer;
import com.ui.MainWindow;
import com.utils.ClassDescription;
import com.utils.PluginDescription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final ObservableList<ClassDescription> classList;
    private static final ObservableList<SerializerDescription> serializerList;
    private static final ArrayList<PluginDescription> pluginsList = new ArrayList<>();

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
        serializerList = FXCollections.observableArrayList();
        serializerList.addAll(
                new SerializerDescription(BinarySerializer.class, new ArrayList<>(List.of(".bin"))),
                new SerializerDescription(XMLSerializer.class, new ArrayList<>(List.of(".xml"))),
                new SerializerDescription(TextSerializer.class, new ArrayList<>(List.of(".txt")))
        );
    }

    public static ObservableList<SerializerDescription> getSerializerList() {
        return serializerList;
    }

    public static ObservableList<ClassDescription> getClassList() {
        return classList;
    }

    public static ArrayList<PluginDescription> getPluginsList() {
        return pluginsList;
    }

    public static void main(String[] args) {
        /* launch main window of this app */
        MainWindow.mainWindow(args);
    }

}
