package com.utils;

import com.Main;
import com.plugins.ArchivePlugin;
import com.serializers.Serializer;
import com.serializers.SerializerDescription;
import com.ui.MainWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public abstract class SerializeEventsBaseClass {

    public Serializer getSerializerByFileDescription(SerializeFileDescription fileDescription) throws InstantiationException, IllegalAccessException {
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

    public ArchivePlugin getArchivePluginByFileDescription(SerializeFileDescription fileDescription) throws InstantiationException, IllegalAccessException {
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

    public void serializeDataOutputStream(Serializer serializer, ListView<ClassDescription> objectListView, OutputStream serializeOutputStream) throws IOException {
        ArrayList<Object> listToSerialize = new ArrayList<>();
        for (ClassDescription objectToSerialize : objectListView.getItems()) {
            listToSerialize.add(objectToSerialize.getObject_For_Description());
        }
        serializer.serialize(listToSerialize, serializeOutputStream);
    }

    public void deserializeDataInputStream(Serializer deserializer, ListView<ClassDescription> objectListView, InputStream serializeInputStream) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        ArrayList<Object>deserializedList = deserializer.deserialize(serializeInputStream);
        ObservableList<ClassDescription> deserializedObservableList = FXCollections.observableArrayList();
        for (Object deserializedObject : deserializedList) {
            deserializedObservableList.add(new ClassDescription(deserializedObject));
        }
        objectListView.setItems(deserializedObservableList);
    }
}
