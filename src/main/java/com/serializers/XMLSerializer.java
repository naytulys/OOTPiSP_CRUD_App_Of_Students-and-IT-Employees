package com.serializers;

import com.annotations.LocalizedName;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

@LocalizedName("XML Serializer")
public class XMLSerializer implements Serializer{
    @Override
    public void serialize(ArrayList<Object> objectListToWrite, OutputStream outputStream) {
        try {
            XMLEncoder xmlEncoder = new XMLEncoder(outputStream);
            xmlEncoder.writeObject(objectListToWrite);
            xmlEncoder.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Object> deserialize(InputStream inputStream) {
        Object deserializedObject = null;
        try {
            XMLDecoder xmlDecoder = new XMLDecoder(inputStream);
            deserializedObject = xmlDecoder.readObject();
            xmlDecoder.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (ArrayList<Object>)deserializedObject;
    }
}
