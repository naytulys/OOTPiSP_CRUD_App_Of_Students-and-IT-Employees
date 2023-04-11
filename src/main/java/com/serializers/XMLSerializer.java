package com.serializers;

import com.annotations.LocalizedName;
import com.ui.events.ShowMessage;
import javafx.stage.Stage;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

@LocalizedName("XML Serializer")
public class XMLSerializer implements Serializer{
    @Override
    public void serialize(ArrayList<Object> objectListToWrite, OutputStream outputStream) {
        try (XMLEncoder xmlEncoder = new XMLEncoder(outputStream)){
            xmlEncoder.writeObject(objectListToWrite);
        }
    }

    @Override
    public ArrayList<Object> deserialize(InputStream inputStream) {
        Object deserializedObject;
        try (XMLDecoder xmlDecoder = new XMLDecoder(inputStream)) {
            deserializedObject = xmlDecoder.readObject();
        }
        return (ArrayList<Object>)deserializedObject;
    }
}
