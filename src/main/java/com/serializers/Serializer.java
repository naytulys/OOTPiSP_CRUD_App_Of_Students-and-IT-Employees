package com.serializers;

import com.annotations.LocalizedName;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

@LocalizedName("Serializer interface")
public interface Serializer {

    void serialize(ArrayList<Object> objectListToWrite, OutputStream outputStream);

    ArrayList<Object> deserialize(InputStream inputStream);


}
