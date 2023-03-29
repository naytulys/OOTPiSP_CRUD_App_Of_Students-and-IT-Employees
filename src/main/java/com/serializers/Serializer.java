package com.serializers;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public interface Serializer {

    void serialize(ArrayList<Object> objectListToWrite, OutputStream outputStream);

    ArrayList<Object> deserialize(InputStream inputStream);
}
