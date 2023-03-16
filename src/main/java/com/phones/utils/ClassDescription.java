package com.phones.utils;

import com.phones.annotations.LocalizedName;

public class ClassDescription {
    private final Class<?> selected_Class;
    private Object object_For_Description;

    public void setObject_For_Description(Object object_For_Description) {
        this.object_For_Description = object_For_Description;
    }

    public ClassDescription(Class<?> selected_Class) {
        this.selected_Class = selected_Class;
    }

    public ClassDescription(Object object_For_Description) {
        this.object_For_Description = object_For_Description;
        this.selected_Class = object_For_Description.getClass();
    }

    public String getLocalName() {
        String localName;
        if (selected_Class.isAnnotationPresent(LocalizedName.class)) {
            localName = selected_Class.getAnnotation(LocalizedName.class).value();
        } else {
            localName = selected_Class.getSimpleName();
        }
        return localName;
    }

    public Class<?> getSelected_Class() {
        return selected_Class;
    }

    public Object getObject_For_Description() {
        return object_For_Description;
    }
}
