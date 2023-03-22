package com.ui.fields;

import com.utils.FieldOptions;
import javafx.scene.Node;
import javafx.scene.control.TextField;

import java.lang.reflect.InvocationTargetException;

public class TextFieldHandle implements FieldHandle {

    private final TextField textField;
    private final Object fieldObject;
    private final FieldOptions fieldOptions;

    public TextFieldHandle(Object objectToInspect, FieldOptions field) {
        this.fieldObject = objectToInspect;
        this.fieldOptions = field;
        textField = new TextField();

        Object ObjectValue = getValueFromObject();
        setObjectToElement(ObjectValue);

        textField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                if (this.fieldOptions.getFieldClassType().equals(Integer.class)) {
                    fieldOptions.getSet().invoke(this.fieldObject, Integer.parseInt(newValue.equals("") ? "0" : newValue));
                } else if (this.fieldOptions.getFieldClassType().equals(Double.class)) {
                    fieldOptions.getSet().invoke(this.fieldObject, Double.parseDouble(newValue.equals("") ? "0" : newValue));
                } else {
                    fieldOptions.getSet().invoke(this.fieldObject, newValue);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    public Object getValueFromObject() {
        return this.fieldOptions.getFieldValue();
    }

    public void setObjectToElement(Object objectToWrite) {
        textField.setText((objectToWrite != null ? objectToWrite.toString() : ""));
    }

    public Object getValueFromElement() {
        return textField.getText();
    }

    public Node getControl() {
        return this.textField;
    }
}
