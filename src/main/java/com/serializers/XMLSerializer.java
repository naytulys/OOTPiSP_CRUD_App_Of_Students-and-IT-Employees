package com.serializers;

import com.annotations.LocalizedName;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

@LocalizedName("XML Serializer")
public class XMLSerializer implements Serializer{
    @Override
    public void serialize(ArrayList<Object> objectListToWrite, OutputStream outputStream) {

    }

    @Override
    public ArrayList<Object> deserialize(InputStream inputStream) {
        return null;
    }
}
