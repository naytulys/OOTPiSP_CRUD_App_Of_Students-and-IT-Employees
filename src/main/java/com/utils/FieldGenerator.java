package com.utils;


import com.ui.fields.BooleanFieldHandle;
import com.ui.fields.FieldHandle;
import com.ui.fields.TextFieldHandle;
import javafx.scene.layout.Pane;

public class FieldGenerator extends Pane {

    public FieldGenerator(Object objectToInspect, FieldOptions field) {
        FieldHandle newField;
        switch (field.get_Field_User_Interface_Type()) {
            case TEXT:
                newField = new TextFieldHandle(objectToInspect, field);
                break;
            case BOOLEAN:
                newField = new BooleanFieldHandle(objectToInspect, field);
                break;
            default:
                newField = null;
                break;
        }
        if (newField != null) {
            this.getChildren().add(newField.getControl());
        }
    }
}
