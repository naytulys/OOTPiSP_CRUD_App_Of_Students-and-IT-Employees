package com.phones.utils;

import com.phones.annotations.LocalizedName;

import java.lang.reflect.Method;
import java.util.List;

public class FieldOptions {

    private String methodName;
    private Object fieldValue;
    private Class<?> fieldClassType;
    private Method get, set;

    public FieldOptions(String methodName) {
        this.methodName = methodName;
    }

    public FieldType getFieldType() {

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

}
