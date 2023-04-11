package com.ui.events;

import com.Main;
import com.plugins.ArchivePlugin;
import com.serializers.Serializer;
import com.serializers.SerializerDescription;
import com.ui.MainWindow;
import com.utils.ClassDescription;
import com.utils.CustomFileChooserFilter;
import com.utils.PluginDescription;
import com.utils.SerializeFileDescription;
import javafx.scene.control.ListView;
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

    private Serializer getSerializerByFileDescription(SerializeFileDescription fileDescription) throws InstantiationException, IllegalAccessException {
        if (fileDescription.getSerializeFileExtension() == null){
            return null;
        }
        Serializer resultSerializer = null;
        for (SerializerDescription serializerDescription : Main.getSerializerList()) {
            if (serializerDescription.getExtensionsToSerialize().contains(fileDescription.getSerializeFileExtension())) {
                resultSerializer = serializerDescription.getSerializer().newInstance();
            }
        }
        return resultSerializer;
    }

    private ArchivePlugin getArchivePluginByFileDescription(SerializeFileDescription fileDescription) throws InstantiationException, IllegalAccessException {
        if (fileDescription.getArchiveExtension() == null){
            return null;
        }
        ArchivePlugin resultArchivePlugin = null;
        for (PluginDescription pluginDescription : MainWindow.getPluginsList()) {
            if (pluginDescription.getArchiveExtension().contains(fileDescription.getArchiveExtension())) {
                resultArchivePlugin = pluginDescription.getArchivePlugin().newInstance();
            }
        }
        return resultArchivePlugin;
    }

    private void serializeDataOutputStream(Serializer serializer, ListView<ClassDescription> objectListView, OutputStream serializeOutputStream) throws IOException {
        ArrayList<Object> listToSerialize = new ArrayList<>();
        for (ClassDescription objectToSerialize : objectListView.getItems()) {
            listToSerialize.add(objectToSerialize.getObject_For_Description());
        }
        serializer.serialize(listToSerialize, serializeOutputStream);
    }
}

