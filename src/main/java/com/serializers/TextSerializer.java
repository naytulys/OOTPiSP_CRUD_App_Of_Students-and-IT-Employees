package com.serializers;

import com.annotations.LocalizedName;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

@LocalizedName("Text Serializer")
public class TextSerializer implements Serializer{
    @Override
    public void serialize(ArrayList<Object> objectListToWrite, OutputStream outputStream) {

    }

    @Override
    public ArrayList<Object> deserialize(InputStream inputStream) {
        return null;
    }
}
