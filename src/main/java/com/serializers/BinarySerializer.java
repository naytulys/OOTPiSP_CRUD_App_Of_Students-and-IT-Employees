package com.serializers;

import com.annotations.LocalizedName;
import com.ui.events.ShowMessage;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

@LocalizedName("Binary Serializer")
public class BinarySerializer implements Serializer {

    @Override
    public void serialize(Stage parentStage, ArrayList<Object> objectListToWrite, OutputStream outputStream) {
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(new ArrayList<>(objectListToWrite));
        } catch (IOException e) {
            new ShowMessage(parentStage, "There is some exceptions while binary serialization.");
        }
    }

    @Override
    public ArrayList<Object> deserialize(Stage parentStage, InputStream inputStream) {
        Object deserializedObject;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            deserializedObject = objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            new ShowMessage(parentStage, "There is some exceptions while binary deserialization.");
            deserializedObject = null;
        }
        return (ArrayList<Object>) deserializedObject;
    }
}
