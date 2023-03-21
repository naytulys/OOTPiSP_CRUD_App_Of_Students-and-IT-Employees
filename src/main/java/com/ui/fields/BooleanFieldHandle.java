package com.ui.fields;

import com.utils.FieldOptions;
import javafx.scene.control.CheckBox;

import java.lang.reflect.InvocationTargetException;

public class BooleanFieldHandle implements FieldHandle {

    private final CheckBox checkBox;
    private final Object fieldObject;
    private final FieldOptions fieldOptions;

    public BooleanFieldHandle(Object objectToInspect, FieldOptions field) {
        checkBox = new CheckBox();
        this.fieldObject = objectToInspect;
        this.fieldOptions = field;

        Object ObjectValue = getValueFromObject(this.fieldObject);
        setObjectToElement(ObjectValue);

        checkBox.setOnAction(actionEvent -> {
            try {
                fieldOptions.getSet().invoke(this.fieldObject, getValueFromElement());
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    public Object getValueFromObject(Object fieldObject) {
        try {
            return fieldOptions.getGet().invoke(fieldObject);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setObjectToElement(Object objectToWrite) {
        checkBox.setSelected(objectToWrite == null ? false :  (boolean) objectToWrite);
    }

    public Object getValueFromElement() {
        return checkBox.isSelected();
    }

    public CheckBox getControl() {
        return this.checkBox;
    }
}
