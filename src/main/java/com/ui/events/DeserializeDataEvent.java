package com.ui.events;

import com.Main;
import com.plugins.ArchivePlugin;
import com.serializers.Serializer;
import com.serializers.SerializerDescription;
import com.utils.ClassDescription;
import com.utils.CustomFileChooserFilter;
import com.utils.PluginDescription;
import com.utils.SerializeFileDescription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class DeserializeDataEvent implements ButtonEvent {
    public void onClick(Stage parentStage, Class<?> selectedClass, ListView<ClassDescription> objectListView) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open Resource File");
        fileChooser.setAcceptAllFileFilterUsed(false);
        for (SerializerDescription serializerDescription : Main.getSerializerList()) {
            CustomFileChooserFilter filter = new CustomFileChooserFilter(serializerDescription, Main.getPluginsList());
            fileChooser.addChoosableFileFilter(filter);
        }
        int openDialogResult = fileChooser.showOpenDialog(null);
        if (openDialogResult == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            SerializeFileDescription fileDescription = new SerializeFileDescription(selectedFile, Main.getSerializerList(), Main.getPluginsList());
            if (unpackingDataMethod(parentStage, fileDescription)){
                if (deserializeDataMethod(parentStage, fileDescription, objectListView)){
                    new ShowMessage(parentStage, "Data deserialization done.");
                }
            }
        }
    }

    private boolean deserializeDataMethod(Stage parentStage, SerializeFileDescription fileDescription, ListView<ClassDescription> objectListView){
        if (fileDescription.getSerializeFileExtension() == null){
            return false;
        }
        ArrayList<Object> deserializedList = null;
        for (SerializerDescription serializerDescription : Main.getSerializerList()) {
            if (serializerDescription.getExtensionsToSerialize().contains(fileDescription.getSerializeFileExtension())) {
                try (FileInputStream in = new FileInputStream(fileDescription.getSerializeFilePath())) {
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
                }
            }
        }
        return deserializedList != null;
    }

    private boolean unpackingDataMethod(Stage parentStage, SerializeFileDescription fileDescription){
        if (fileDescription.getArchiveExtension() == null){
            return true;
        }
        boolean isUnpackingDone = false;
        for (PluginDescription pluginDescription : Main.getPluginsList()) {
            if (pluginDescription.getArchiveExtension().contains(fileDescription.getArchiveExtension())) {
                try(FileInputStream inputStream = new FileInputStream(fileDescription.getFileAbsolutePath());
                    FileOutputStream outputStream = new FileOutputStream(fileDescription.getSerializeFilePath())
                ) {
                    ArchivePlugin archivePlugin = pluginDescription.getArchivePlugin().newInstance();
                    archivePlugin.decompress(inputStream, outputStream);
                    isUnpackingDone = true;
                } catch (InstantiationException | IllegalAccessException | IOException e) {
                    new ShowMessage(parentStage, "There is some exceptions while serialization.");
                }
            }
        }
        return isUnpackingDone;
    }
}
