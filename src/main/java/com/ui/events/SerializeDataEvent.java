package com.ui.events;

import com.Main;
import com.serializers.Serializer;
import com.serializers.SerializerDescription;
import com.utils.ClassDescription;
import com.utils.CustomFileChooserFilter;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class SerializeDataEvent implements ButtonEvent {
    public void onClick(Stage parentStage, Class<?> selectedClass, ListView<ClassDescription> objectListView) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Resource File");
        fileChooser.setAcceptAllFileFilterUsed(false);
        for (SerializerDescription serializerDescription : Main.getSerializerList()) {
            CustomFileChooserFilter filter = new CustomFileChooserFilter(serializerDescription);
            fileChooser.addChoosableFileFilter(filter);
        }
        int saveDialogResult = fileChooser.showSaveDialog(null);
        if (saveDialogResult == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            String fileExtension = filePath.substring(filePath.lastIndexOf('.'));
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
