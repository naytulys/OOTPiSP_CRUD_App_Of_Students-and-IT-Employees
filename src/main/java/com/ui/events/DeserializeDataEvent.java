package com.ui.events;

import com.Main;
import com.serializers.Serializer;
import com.serializers.SerializerDescription;
import com.utils.ClassDescription;
import com.utils.CustomFileChooserFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class DeserializeDataEvent implements ButtonEvent {
    public void onClick(Stage parentStage, Class<?> selectedClass, ListView<ClassDescription> objectListView) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open Resource File");
        fileChooser.setAcceptAllFileFilterUsed(false);
        for (SerializerDescription serializerDescription : Main.getSerializerList()) {
            CustomFileChooserFilter filter = new CustomFileChooserFilter(serializerDescription);
            fileChooser.addChoosableFileFilter(filter);
        }
        int openDialogResult = fileChooser.showOpenDialog(null);
        if (openDialogResult == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            String fileExtension = filePath.substring(filePath.lastIndexOf('.'));
            for (SerializerDescription serializerDescription : Main.getSerializerList()) {
                if (serializerDescription.getExtensionsToSerialize().contains(fileExtension)) {
                    ArrayList<Object> deserializedList;
                    try (FileInputStream in = new FileInputStream(filePath)) {
                        Serializer deserializer = serializerDescription.getSerializer().newInstance();
                        deserializedList = deserializer.deserialize(parentStage, in);
                    } catch (InstantiationException | IllegalAccessException | IOException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException e) {
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
