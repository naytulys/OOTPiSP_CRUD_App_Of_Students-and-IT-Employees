package com.serializers;

import com.annotations.LocalizedName;

import java.io.*;
import java.util.ArrayList;

@LocalizedName("Binary Serializer")
public class BinarySerializer implements Serializer {

    @Override
    public void serialize(ArrayList<Object> objectListToWrite, OutputStream outputStream) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(new ArrayList<>(objectListToWrite));
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Object> deserialize(InputStream inputStream) {
        Object deserializedObject = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            deserializedObject = objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (ArrayList<Object>) deserializedObject;
    }
}
