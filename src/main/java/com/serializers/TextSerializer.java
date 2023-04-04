package com.serializers;

import com.annotations.LocalizedName;
import com.ui.events.ShowMessage;
import com.utils.FieldOptions;
import com.utils.FieldsParser;
import com.utils.InnerClassDependencyRecord;
import com.utils.InnerClassesDependencyRestorer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

@LocalizedName("Text Serializer")
public class TextSerializer implements Serializer {

    private final InnerClassesDependencyRestorer dependencyRestorer = new InnerClassesDependencyRestorer();

    @Override
    public void serialize(Stage parentStage, ArrayList<Object> objectListToWrite, OutputStream outputStream) {
        try(OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream)) {
            for (Object object : objectListToWrite) {
                outputStreamWriter.write(serializeObject(object, objectListToWrite));
            }
        } catch (IOException e) {
            new ShowMessage(parentStage, "There is some exceptions while text serialization.");
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
        this.dependencyRestorer.clear();
        ArrayList<Object> resultObjectList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            Object currentObject = null;
            HashMap<String, String> setterNameToValueMap = new HashMap<>();
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("[")) {
                    String className = line.substring(1, line.length() - 1);
                    currentObject = Class.forName(className).getConstructor().newInstance();
                } else if (line.contains("=")) {
                    String fieldSetterName = line.substring(0, line.indexOf("="));
                    String fieldValue = line.substring(line.indexOf("=") + 1);
                    setterNameToValueMap.put(fieldSetterName, fieldValue);
                } else if (currentObject != null) {
                    /* set setterNameToValueMap to current object */
                    setObjectFieldsExceptInnerClasses(currentObject, setterNameToValueMap);
                    resultObjectList.add(currentObject);
                    currentObject = null;
                    setterNameToValueMap = new HashMap<>();
                }
            }
            /* restore dependencies */
            this.dependencyRestorer.restoreInnerClassesDependencies(resultObjectList);
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            new ShowMessage(parentStage, "There is some exceptions while text deserialization.");
            resultObjectList = null;
        }
        return resultObjectList;
    }

    private void setObjectFieldsExceptInnerClasses(Object objectToFill, HashMap<String, String> setterNameToValueMap) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        ArrayList<FieldOptions> objectFieldOptions = FieldsParser.parseFields(objectToFill);
        for (FieldOptions fieldOption : objectFieldOptions) {
            /* serialize method implementation
             * using indexOf method for inner classes
             * that's why resolving links for inner classes fields
             * possible only when all objects are deserialized
             * */
            String fieldSetterName = fieldOption.getSet().getName();
            if (setterNameToValueMap.containsKey(fieldSetterName)) {
                String value = setterNameToValueMap.get(fieldSetterName);
                if (fieldOption.getFieldUserInterfaceType() == FieldOptions.FieldType.INNERCLASS) {
                    InnerClassDependencyRecord record = new InnerClassDependencyRecord(objectToFill, fieldOption, Integer.parseInt(value));
                    this.dependencyRestorer.add(record);
                } else {
                    Object fieldValue = createObjectByClassNameAndStringValue(fieldOption.getFieldClassType(), value);
                    fieldOption.getSet().invoke(objectToFill, fieldValue);
                }
            }
        }
    }

    private Object createObjectByClassNameAndStringValue(Class<?> className, String value) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        className = FieldsParser.convert_Primitive_To_References_Types(className);
        /* Without value null checking
         * invoke might produce InvocationTargetException
         * when class represented by className is Enum type class
         * */
        if (value.equals("null")) {
            return null;
        }
        /* for String class is not exist declaration for valueOf(String s) method
         * getMethod throw NoSuchMethodException without this checking
         * */
        if (className == String.class) {
            return value;
        }
        Method valueOfMethod = className.getMethod("valueOf", String.class);
        return valueOfMethod.invoke(null, value);
    }
}
