package com.serializers;

import com.annotations.LocalizedName;
import javafx.stage.Stage;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

@LocalizedName("Serializer interface")
public interface Serializer {

    void serialize(Stage parentStage, ArrayList<Object> objectListToWrite, OutputStream outputStream);

    ArrayList<Object> deserialize(Stage parentStage, InputStream inputStream);


}
