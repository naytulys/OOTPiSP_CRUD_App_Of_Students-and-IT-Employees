package com.phones.ui.events;

import com.phones.utils.ClassDescription;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class EditButtonEvent {
    public void onClick(Stage parentStage, Class<?> selectedClass, ListView<ClassDescription> objectListView) {
        ClassDescription selectedItem = objectListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // open edit window
        } else {
            // show error message
        }
    }
}
