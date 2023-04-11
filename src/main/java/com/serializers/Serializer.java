package com.serializers;

import com.annotations.LocalizedName;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

@LocalizedName("Serializer interface")
public interface Serializer {

    void serialize(ArrayList<Object> objectListToWrite, OutputStream outputStream) throws IOException;

    ArrayList<Object> deserialize(InputStream inputStream) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException;


}
