package com.phones.ui.events;

import com.phones.utils.ClassDescription;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class RemoveButtonEvent {
    public void onClick(Stage parentStage, Class<?> selectedClass, ListView<ClassDescription> objectListView) {
        boolean is_Removed = objectListView.getItems().remove(objectListView.getSelectionModel().getSelectedItem());
        if (!is_Removed) {
            new ShowMessage(parentStage, "Item wasn't selected");
        }
    }
}
