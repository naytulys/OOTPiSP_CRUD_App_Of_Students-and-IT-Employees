package com.phones.ui.fields;

import com.phones.utils.FieldOptions;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;

import java.lang.reflect.InvocationTargetException;

public class BooleanFieldHandle {

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

    private Object getValueFromObject(Object fieldObject) {
        try {
            return fieldOptions.getGet().invoke(fieldObject);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setObjectToElement(Object objectToWrite) {
        checkBox.setSelected((boolean) objectToWrite);
    }

    private Object getValueFromElement() {
        return checkBox.isSelected();
    }

    public CheckBox getCheckBox() {
        return this.checkBox;
    }
}
