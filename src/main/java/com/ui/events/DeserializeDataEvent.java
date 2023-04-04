package com.ui.events;

import com.Main;
import com.serializers.Serializer;
import com.serializers.SerializerDescription;
import com.utils.ClassDescription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
                    ArrayList<Object> deserializedList;
                    try (FileInputStream in = new FileInputStream(filePath)) {
                        Serializer deserializer = serializerDescription.getSerializer().newInstance();
                        deserializedList = deserializer.deserialize(parentStage, in);
                    } catch (InstantiationException | IllegalAccessException | IOException e) {
                        new ShowMessage(parentStage, "There is some exceptions while deserialization.");
                        deserializedList = null;
                    }
                    if (deserializedList != null) {
                        ObservableList<ClassDescription> deserializedObservableList = FXCollections.observableArrayList();
                        for (Object deserializedObject : deserializedList) {
                            deserializedObservableList.add(new ClassDescription(deserializedObject));
                        }
                        objectListView.setItems(deserializedObservableList);
                        new ShowMessage(parentStage, "Data deserialization done.");
                    }
                }
            }
        }
    }
}
