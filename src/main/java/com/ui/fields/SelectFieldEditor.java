package com.ui.fields;

import com.annotations.LocalizedName;
import com.utils.FieldOptions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;


public class SelectFieldEditor implements FieldHandle {


    private final ComboBox<ComboBoxItem> comboBox;
    private final ObservableList<ComboBoxItem> enumFields;
    private final Object fieldObject;
    private final FieldOptions fieldOptions;


    public SelectFieldEditor(Object objectToInspect, FieldOptions field) {
        comboBox = new ComboBox<>();
        this.fieldObject = objectToInspect;
        this.fieldOptions = field;

        enumFields = FXCollections.observableArrayList();
        if (field.getFieldClassType().isEnum()) {
            Enum<?>[] enumValues = (Enum<?>[]) fieldOptions.getFieldClassType().getEnumConstants();
            for (Enum<?> enumValue : enumValues) {
                enumFields.add(new ComboBoxItem(enumValue));
            }
            comboBox.setItems(enumFields);
            Object ObjectValue = getValueFromObject(this.fieldObject);
            setObjectToElement(ObjectValue);
        }

        comboBox.setOnAction(actionEvent -> {
            try {
                fieldOptions.getSet().invoke(this.fieldObject, getValueFromElement());
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });

    }

    public Object getValueFromElement() {
        return comboBox.getValue().enumField;
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
        ComboBoxItem foundItem = null;
        for (ComboBoxItem item : enumFields) {
            if (item.getEnumField() == objectToWrite) {
                foundItem = item;
            }
        }
        comboBox.getSelectionModel().select(foundItem);
    }

    public Node getControl() {
        return comboBox;
    }

    private record ComboBoxItem(Enum<?> enumField) {

        Enum<?> getEnumField() {
            return enumField;
        }

        @Override
        public String toString() {
            Class<?> enumType = enumField.getClass();
            String localizedName = enumField.toString();
            try {
                Field enumConstant = enumType.getField(localizedName);
                if (enumConstant.isAnnotationPresent(LocalizedName.class)) {
                    localizedName = enumConstant.getAnnotation(LocalizedName.class).value();
                }
            } catch (NoSuchFieldException ignored) {
            }
            return localizedName;
        }
    }
}
