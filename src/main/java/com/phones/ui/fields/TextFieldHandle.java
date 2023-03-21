package com.phones.ui.fields;

import com.phones.utils.FieldOptions;
import javafx.scene.Node;
import javafx.scene.control.TextField;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Pattern;

public class TextFieldHandle {

    private final TextField textField;
    private final Object fieldObject;
    private final FieldOptions fieldOptions;
    private static final int MAX_INT_LENGTH = 9;
    private static final int MAX_DOUBLE_LENGTH = 9;


    public TextFieldHandle(Object objectToInspect, FieldOptions field) {
        this.fieldObject = objectToInspect;
        this.fieldOptions = field;
        textField = new TextField();

        Object ObjectValue = getValueFromObject(this.fieldObject);
        setObjectToElement(ObjectValue);

        textField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            Check_Int(oldValue, newValue, textField);
            try {
                if (this.fieldOptions.getFieldClassType().equals(Integer.class)) {
                    Check_Int(oldValue, newValue, textField);
                    fieldOptions.getSet().invoke(this.fieldObject, Integer.parseInt(textField.getText()));
                } else if (this.fieldOptions.getFieldClassType().equals(Double.class)) {
                    Check_Double(oldValue, newValue, textField);
                    fieldOptions.getSet().invoke(this.fieldObject, Double.parseDouble(textField.getText()));
                } else {
                    fieldOptions.getSet().invoke(this.fieldObject, textField.getText());
                }
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
        textField.setText((objectToWrite != null ? objectToWrite.toString() : ""));
    }

    private Object getValueFromElement() {
        return textField.getText();
    }

    public void Check_Double(String oldValue, String newValue, TextField d) {
        String regDate = "([0-9]+\\.?[0-9]*)|(^)";
        Pattern pattern = Pattern.compile(regDate);
        if (pattern.matcher(newValue).matches() && newValue.length() <= MAX_DOUBLE_LENGTH) {
            d.setText(newValue);
        }
        else {
            d.setText(oldValue.equals("") ? "0" : oldValue);
        }
    }

    public void Check_Int(String oldValue, String newValue, TextField d) {
        String regDate = "([1-9]+[0-9]*)|(^)";
        Pattern pattern = Pattern.compile(regDate);
        if (pattern.matcher(newValue).matches() && newValue.length() <= MAX_INT_LENGTH) {
            d.setText(newValue);
        }
        else {
            d.setText(oldValue.equals("") ? "0" : oldValue);
        }
    }
}
