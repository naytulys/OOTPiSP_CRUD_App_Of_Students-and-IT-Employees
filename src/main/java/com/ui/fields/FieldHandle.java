package com.ui.fields;

import javafx.scene.Node;

public interface FieldHandle {

    Object getValueFromObject();

    void setObjectToElement(Object objectToWrite);

    Object getValueFromElement();

    Node getControl();
}
