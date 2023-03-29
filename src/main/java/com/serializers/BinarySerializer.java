package com.serializers;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class BinarySerializer implements Serializer{
    @Override
    public void serialize(ArrayList<Object> objectListToWrite, OutputStream outputStream) {

    }

    @Override
    public ArrayList<Object> deserialize(InputStream inputStream) {
        return null;
    }
}
