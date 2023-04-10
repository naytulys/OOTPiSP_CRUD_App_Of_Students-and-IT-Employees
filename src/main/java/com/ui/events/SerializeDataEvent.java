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
            boolean isSerializationDone = serializeDataMethod(parentStage, fileDescription, objectListView);
            if (isSerializationDone){
                boolean isArchivingDone = archiveDataMethod(parentStage, fileDescription);
                if (isArchivingDone){
                    new ShowMessage(parentStage, "Data Serialization Done");
                }
            }
        }
    }

    private boolean serializeDataMethod(Stage parentStage, SerializeFileDescription fileDescription, ListView<ClassDescription> objectListView){
        if (fileDescription.getSerializeFileExtension() == null){
            return false;
        }
        boolean isSerializationDone = false;
        for (SerializerDescription serializerDescription : Main.getSerializerList()) {
            if (serializerDescription.getExtensionsToSerialize().contains(fileDescription.getSerializeFileExtension())) {
                ArrayList<Object> listToSerialize = new ArrayList<>();
                for (ClassDescription objectToSerialize : objectListView.getItems()) {
                    listToSerialize.add(objectToSerialize.getObject_For_Description());
                }
                try(FileOutputStream outputStream = new FileOutputStream(fileDescription.getSerializeFilePath())
                ) {
                    Serializer serializer = serializerDescription.getSerializer().newInstance();
                    serializer.serialize(parentStage, listToSerialize, outputStream);
                    isSerializationDone = true;
                } catch (InstantiationException | IllegalAccessException | IOException e) {
                    new ShowMessage(parentStage, "There is some exceptions while serialization.");
                }
            }
        }
        return isSerializationDone;
    }

    private boolean archiveDataMethod(Stage parentStage, SerializeFileDescription fileDescription){
        if (fileDescription.getArchiveExtension() == null){
            return true;
        }
        boolean isArchivingDone = false;
        for (PluginDescription pluginDescription : MainWindow.getPluginsList()) {
            if (pluginDescription.getArchiveExtension().contains(fileDescription.getArchiveExtension())) {
                try(FileInputStream inputStream = new FileInputStream(fileDescription.getSerializeFilePath());
                    FileOutputStream outputStream = new FileOutputStream(fileDescription.getFileAbsolutePath())
                ) {
                    ArchivePlugin archivePlugin = pluginDescription.getArchivePlugin().newInstance();
                    archivePlugin.compress(inputStream, outputStream);
                    isArchivingDone = true;
                } catch (InstantiationException | IllegalAccessException | IOException e) {
                    new ShowMessage(parentStage, "There is some exceptions while serialization.");
                }
            }
        }
        return isArchivingDone;
    }
}
