package com.phones.utils;


import com.phones.ui.fields.BooleanFieldHandle;
import com.phones.ui.fields.TextFieldHandle;
import javafx.scene.layout.Pane;

public class FieldGenerator extends Pane {

    public FieldGenerator(Object objectToInspect, FieldOptions field) {
        BooleanFieldHandle booleanFieldHandle = null;
        TextFieldHandle textFieldHandle = null;
        switch (field.get_Field_User_Interface_Type()) {
            case TEXT:
                textFieldHandle = new TextFieldHandle(objectToInspect, field);
                break;
            case BOOLEAN:
                booleanFieldHandle = new BooleanFieldHandle(objectToInspect, field);
                break;

        }

        if (booleanFieldHandle != null) {
            getChildren().add(booleanFieldHandle);
        }
        if (textFieldHandle != null) {
            getChildren().add(textFieldHandle);
        }

    }
}
