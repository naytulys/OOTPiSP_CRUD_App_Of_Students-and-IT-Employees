package com.serializers;

import com.annotations.LocalizedName;
import javafx.stage.Stage;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

@LocalizedName("Text Serializer")
public class TextSerializer implements Serializer{
    @Override
    public void serialize(Stage parentStage, ArrayList<Object> objectListToWrite, OutputStream outputStream) {

    }

    @Override
    public ArrayList<Object> deserialize(Stage parentStage, InputStream inputStream) {
        return null;
    }
}
