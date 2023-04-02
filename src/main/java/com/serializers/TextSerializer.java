package com.serializers;

import com.annotations.LocalizedName;
import com.utils.FieldOptions;
import com.utils.FieldsParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

@LocalizedName("Text Serializer")
public class TextSerializer implements Serializer{
    @Override
    public void serialize(Stage parentStage, ArrayList<Object> objectListToWrite, OutputStream outputStream) {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        try {
            for (Object object : objectListToWrite) {
                outputStreamWriter.write(serializeObject(object, objectListToWrite));
            }
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String serializeObject(Object objectToSerialize, ArrayList<Object> listToSerialize) {
        ObservableList<Object> objectObservableList = FXCollections.observableList(listToSerialize);
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<FieldOptions> fields = FieldsParser.parseFields(objectToSerialize);
        stringBuilder.append('[').append(objectToSerialize.getClass().getName()).append("]\n");
        for (FieldOptions currentField : fields) {
            String fieldSetter = currentField.getSet().getName();
            String fieldValue = String.valueOf(currentField.getFieldValue());
            if (currentField.getFieldUserInterfaceType() == FieldOptions.FieldType.INNERCLASS) {
                int fieldPosition = objectObservableList.indexOf(currentField.getFieldValue());
                fieldValue = String.valueOf(fieldPosition);
            }
            stringBuilder.append(fieldSetter).append('=').append(fieldValue).append('\n');
        }
        stringBuilder.append('\n');
        return stringBuilder.toString();
    }

    @Override
    public ArrayList<Object> deserialize(Stage parentStage, InputStream inputStream) {
        return null;
    }
}
