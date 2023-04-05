package com.ui.events;

import com.Main;
import com.serializers.Serializer;
import com.serializers.SerializerDescription;
import com.utils.ClassDescription;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class SerializeDataEvent implements ButtonEvent {
    public void onClick(Stage parentStage, Class<?> selectedClass, ListView<ClassDescription> objectListView) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Resource File");
        File selectedFile = fileChooser.showOpenDialog(parentStage);
        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            String fileExtension = filePath.substring(filePath.lastIndexOf('.') + 1);
            for (SerializerDescription serializerDescription : Main.getSerializerList()) {
                if (serializerDescription.getExtensionsToSerialize().contains(fileExtension)) {
                    ArrayList<Object> listToSerialize = new ArrayList<>();
                    for (ClassDescription objectToSerialize : objectListView.getItems()) {
                        listToSerialize.add(objectToSerialize.getObject_For_Description());
                    }
                    try(FileOutputStream outputStream = new FileOutputStream(filePath)
                    ) {
                        Serializer serializer = serializerDescription.getSerializer().newInstance();
                        serializer.serialize(parentStage, listToSerialize, outputStream);
                        new ShowMessage(parentStage, "Data serialization done.");
                    } catch (InstantiationException | IllegalAccessException | IOException e) {
                        new ShowMessage(parentStage, "There is some exceptions while serialization.");
                    }
                }
            }
        }
    }
}
