package com.serializers;

import com.annotations.LocalizedName;
import com.utils.FieldOptions;
import com.utils.FieldsParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

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
        ArrayList<Object> resultObjectList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            Object currentObject = null;
            HashMap<String, String> fields = new HashMap<>();
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("[")) {
                    String className = line.substring(1, line.length() - 1);
                    currentObject = Class.forName(className).newInstance();
                } else if (line.contains("=")) {
                    String fieldName = line.substring(0, line.indexOf("="));
                    String fieldValue = line.substring(line.indexOf("=") + 1);
                    fields.put(fieldName, fieldValue);
                } else {
                    /* set fields to current object */
                    resultObjectList.add(currentObject);
                    currentObject = null;
                    fields = new HashMap<>();
                }
            }
            /* restore dependencies */
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return resultObjectList;
    }
}
