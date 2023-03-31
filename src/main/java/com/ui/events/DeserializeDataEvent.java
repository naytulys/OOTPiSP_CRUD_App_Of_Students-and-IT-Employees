package com.ui.events;

import com.Main;
import com.serializers.Serializer;
import com.serializers.SerializerDescription;
import com.utils.ClassDescription;
import com.utils.EditWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class DeserializeDataEvent implements ButtonEvent {
    public void onClick(Stage parentStage, Class<?> selectedClass, ListView<ClassDescription> objectListView) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File selectedFile = fileChooser.showOpenDialog(parentStage);
        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            String fileExtension = filePath.substring(filePath.lastIndexOf('.') + 1);
            for (SerializerDescription serializerDescription : Main.getSerializerList()) {
                if (serializerDescription.getExtensionsToSerialize().contains(fileExtension)) {
                    try {
                        Serializer deserializer = serializerDescription.getSerializer().newInstance();
                        FileInputStream in = new FileInputStream(filePath);
                        ArrayList<Object> deserializedList = deserializer.deserialize(in);
                        ObservableList<ClassDescription> deserializedObservableList = FXCollections.observableArrayList();
                        for (Object deserializedObject : deserializedList) {
                            deserializedObservableList.add(new ClassDescription(deserializedObject));
                        }
                        objectListView.setItems(deserializedObservableList);
                        in.close();
                    } catch (InstantiationException | IllegalAccessException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
