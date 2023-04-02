package com.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FieldsParser {

    public static Class<?> convert_Primitive_To_References_Types(Class<?> primitiveType) {
        for (Map.Entry<Class<?>, Class<?>> entry : FieldOptions.PRIMITIVETYPEWRAPPER.entrySet()) {
            if (primitiveType.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return primitiveType;
    }

    public static ArrayList<FieldOptions> parseFields(Object objectToParse) {
        ArrayList<FieldOptions> classFields = new ArrayList<>();
        HashMap<String, FieldOptions> fields = new HashMap<>();

        Method[] methods = objectToParse.getClass().getMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (isSet(methodName) || isGet(methodName)) {
                String fieldName = getFieldName(methodName);
                boolean isNewField = !fields.containsKey(fieldName);
                FieldOptions fieldOptions = isNewField ? new FieldOptions(fieldName) : fields.get(fieldName);
                if (isGet(methodName)) {
                    try {
                        Object fieldValue = method.invoke(objectToParse);
                        fieldOptions.setFieldValue(fieldValue);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }

                    Class<?> classType = method.getReturnType();
                    classType = convert_Primitive_To_References_Types(classType);
                    fieldOptions.setFieldClassType(classType);
                    fieldOptions.setGet(method);
                } else {
                    fieldOptions.setSet(method);
                }
                fields.put(fieldName, fieldOptions);
            }
        }
        for (String currentName : fields.keySet()) {
            FieldOptions current = fields.get(currentName);
            if (current.getGet() != null && current.getSet() != null) {
                classFields.add(fields.get(currentName));
            }
        }

        return classFields;
    }

    private static String getFieldName(String methodName) {
        if (methodName.startsWith("is")) {
            return methodName.substring("is".length());
        } else if (methodName.startsWith("get") || methodName.startsWith("set")) {
            return methodName.substring(3);
        } else return methodName;
    }

    private static boolean isSet(String methodName) {
        return methodName.startsWith("set");
    }

    private static boolean isGet(String methodName) {
        return methodName.startsWith("get") || methodName.startsWith("is");
    }

}
