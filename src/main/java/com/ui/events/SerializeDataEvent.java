package com.ui.events;

import com.Main;
import com.plugins.ArchivePlugin;
import com.serializers.Serializer;
import com.serializers.SerializerDescription;
import com.ui.MainWindow;
import com.utils.ClassDescription;
import com.utils.CustomFileChooserFilter;
import com.utils.SerializeEventsBaseClass;
import com.utils.SerializeFileDescription;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;

public class SerializeDataEvent extends SerializeEventsBaseClass implements ButtonEvent {
    public void onClick(Stage parentStage, Class<?> selectedClass, ListView<ClassDescription> objectListView) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Resource File");
        fileChooser.setAcceptAllFileFilterUsed(false);
        for (SerializerDescription serializerDescription : Main.getSerializerList()) {
            CustomFileChooserFilter filter = new CustomFileChooserFilter(serializerDescription, MainWindow.getPluginsList());
            fileChooser.addChoosableFileFilter(filter);
        }
        int saveDialogResult = fileChooser.showSaveDialog(null);
        if (saveDialogResult == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            SerializeFileDescription fileDescription = new SerializeFileDescription(selectedFile, Main.getSerializerList(), MainWindow.getPluginsList());
            try {
                Serializer serializer = getSerializerByFileDescription(fileDescription);
                ArchivePlugin archivePlugin = getArchivePluginByFileDescription(fileDescription);
                if (serializer != null){
                    try(FileOutputStream outputStream = new FileOutputStream(fileDescription.getFileAbsolutePath())) {
                        if (archivePlugin == null) {
                            serializeDataOutputStream(serializer, objectListView, outputStream);
                        } else {
                            ByteArrayOutputStream bufferOutputStream = new ByteArrayOutputStream();
                            serializeDataOutputStream(serializer, objectListView, bufferOutputStream);
                            archivePlugin.compress(new ByteArrayInputStream(bufferOutputStream.toByteArray()), outputStream);
                        }
                        new ShowMessage(parentStage, "Data Serialization Done");
                    }
                }else {
                    new ShowMessage(parentStage, "There is some errors while serialization.");
                }
            } catch (InstantiationException | IllegalAccessException | IOException e) {
                new ShowMessage(parentStage, "There is some exceptions while serialization.");
            }
        }
    }
}

