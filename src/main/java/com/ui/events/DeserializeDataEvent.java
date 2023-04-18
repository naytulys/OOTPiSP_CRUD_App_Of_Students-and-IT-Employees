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
import java.lang.reflect.InvocationTargetException;

public class DeserializeDataEvent extends SerializeEventsBaseClass implements ButtonEvent {
    public void onClick(Stage parentStage, Class<?> selectedClass, ListView<ClassDescription> objectListView) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open Resource File");
        fileChooser.setAcceptAllFileFilterUsed(false);
        for (SerializerDescription serializerDescription : Main.getSerializerList()) {
            CustomFileChooserFilter filter = new CustomFileChooserFilter(serializerDescription, MainWindow.getPluginsList());
            fileChooser.addChoosableFileFilter(filter);
        }
        int openDialogResult = fileChooser.showOpenDialog(null);
        if (openDialogResult == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            SerializeFileDescription fileDescription = new SerializeFileDescription(selectedFile, Main.getSerializerList(), MainWindow.getPluginsList());
            try {
                Serializer serializer = getSerializerByFileDescription(fileDescription);
                ArchivePlugin archivePlugin = getArchivePluginByFileDescription(fileDescription);
                if (serializer != null){
                    try(FileInputStream inputStream = new FileInputStream(fileDescription.getFileAbsolutePath())) {
                        if (archivePlugin == null) {
                            deserializeDataInputStream(serializer, objectListView, inputStream);
                        } else {
                            ByteArrayOutputStream bufferOutputStream = new ByteArrayOutputStream();
                            archivePlugin.decompress(inputStream, bufferOutputStream);
                            deserializeDataInputStream(serializer, objectListView, new ByteArrayInputStream(bufferOutputStream.toByteArray()));
                        }
                        new ShowMessage(parentStage, "Data Deserialization Done");
                    }
                }else {
                    new ShowMessage(parentStage, "There is some errors while deserialization.");
                }
            } catch (InstantiationException | IllegalAccessException | IOException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException | ArrayIndexOutOfBoundsException e) {
                new ShowMessage(parentStage, "There is some exceptions while deserialization.");
            }
        }
    }
}
