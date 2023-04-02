package com.utils;


import com.ui.fields.BooleanFieldHandle;
import com.ui.fields.FieldHandle;
import com.ui.fields.SelectFieldEditor;
import com.ui.fields.TextFieldHandle;
import javafx.scene.layout.Pane;

public class FieldGenerator extends Pane {

    public FieldGenerator(Object objectToInspect, FieldOptions field) {
        FieldHandle newField = switch (field.getFieldUserInterfaceType()) {
            case TEXT -> new TextFieldHandle(objectToInspect, field);
            case BOOLEAN -> new BooleanFieldHandle(objectToInspect, field);
            case LIST -> new SelectFieldEditor(objectToInspect, field);
            default -> null;
        };
        if (newField != null) {
            this.getChildren().add(newField.getControl());
        }
    }
}
