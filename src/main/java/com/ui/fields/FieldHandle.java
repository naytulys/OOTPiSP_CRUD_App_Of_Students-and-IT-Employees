package com.ui.fields;

import com.utils.FieldOptions;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;

public interface FieldHandle {

    Object getValueFromObject(Object fieldObject);

    void setObjectToElement(Object objectToWrite);

    Object getValueFromElement();

    Node getControl();
}
