package com.phones.utils;

import com.phones.annotations.LocalizedName;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldOptions {

    private String methodName;
    private Object fieldValue;
    private Class<?> fieldClassType;
    private Method get, set;

    public static Map<Class<?>, Class<?>> PRIMITIVE_TYPE = new HashMap<>();

    static {
        PRIMITIVE_TYPE.put(Boolean.class, boolean.class);
        PRIMITIVE_TYPE.put(Integer.class, int.class);
        PRIMITIVE_TYPE.put(Byte.class, byte.class);
        PRIMITIVE_TYPE.put(Character.class, char.class);
        PRIMITIVE_TYPE.put(Short.class, short.class);
        PRIMITIVE_TYPE.put(Long.class, long.class);
        PRIMITIVE_TYPE.put(Float.class, float.class);
        PRIMITIVE_TYPE.put(Double.class, double.class);
    }

    public FieldOptions(String methodName) {
        this.methodName = methodName;
    }

    public FieldType get_Field_User_Interface_Type() {
        Class<?> classType = getFieldClassType();
        if (isBoolean(classType)) {
            return FieldOptions.FieldType.BOOLEAN;
        } else if (isTextFieldAssigned(classType)) {
            return FieldOptions.FieldType.TEXT;
        } else if (List.class.isAssignableFrom(classType) || classType.isEnum()) {
            return FieldOptions.FieldType.LIST;
        } else return FieldOptions.FieldType.INNER_CLASS;
    }

    public String getFieldName() {
        if (get.isAnnotationPresent(LocalizedName.class)) {
            return get.getAnnotation(LocalizedName.class).value();
        }
        return getMethodName();
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Method getGet() {
        return get;
    }

    public void setGet(Method get) {
        this.get = get;
    }

    public Method getSet() {
        return set;
    }

    public void setSet(Method set) {
        this.set = set;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }

    public Class<?> getFieldClassType() {
        return fieldClassType;
    }

    public void setFieldClassType(Class<?> fieldClassType) {
        this.fieldClassType = fieldClassType;
    }

    public enum FieldType {
        LIST,
        INNER_CLASS,
        BOOLEAN,
        TEXT
    }

    public static boolean isTextFieldAssigned(Class<?> classType) {
        return PRIMITIVE_TYPE.containsKey(classType) || PRIMITIVE_TYPE.containsValue(classType) || classType.equals(String.class);
    }

    private static boolean isBoolean(Class<?> classType) {
        return (classType == boolean.class || classType == Boolean.class);
    }

}
