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

        Object ObjectValue = getValueFromObject();
        setObjectToElement(ObjectValue);

        checkBox.setOnAction(actionEvent -> {
            try {
                fieldOptions.getSet().invoke(this.fieldObject, getValueFromElement());
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    public Object getValueFromObject() {
        return this.fieldOptions.getFieldValue();
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
