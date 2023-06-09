package com.serializers;

import com.annotations.LocalizedName;
import com.ui.events.ShowMessage;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

@LocalizedName("Binary Serializer")
public class BinarySerializer implements Serializer {

    @Override
    public void serialize(ArrayList<Object> objectListToWrite, OutputStream outputStream) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(new ArrayList<>(objectListToWrite));
        }
    }

    @Override
    public ArrayList<Object> deserialize(InputStream inputStream) throws IOException, ClassNotFoundException {
        Object deserializedObject;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            deserializedObject = objectInputStream.readObject();
        }
        return (ArrayList<Object>) deserializedObject;
    }
}
