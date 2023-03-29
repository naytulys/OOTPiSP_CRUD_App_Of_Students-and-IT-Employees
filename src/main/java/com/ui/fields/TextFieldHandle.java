package com.ui.fields;

import com.utils.FieldOptions;
import javafx.scene.Node;
import javafx.scene.control.TextField;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Pattern;

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
                    try {
                        Integer.parseInt(newValue);
                    }catch (NumberFormatException e){
                        newValue = oldValue;
                    }
                    newValue = newValue.equals("") ? "0" : newValue;
                    textField.setText(newValue);
                    fieldOptions.getSet().invoke(this.fieldObject, Integer.parseInt(textField.getText()));
                } else if (this.fieldOptions.getFieldClassType().equals(Double.class)) {
                    try {
                        Double.parseDouble(newValue);
                    }catch (NumberFormatException e){
                        newValue = oldValue;
                    }
                    newValue = newValue.equals("") ? "0" : newValue;
                    textField.setText(newValue);
                    fieldOptions.getSet().invoke(this.fieldObject, Double.parseDouble(textField.getText()));
                } else {
                    fieldOptions.getSet().invoke(this.fieldObject, textField.getText());
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
