package com.ui.events;

import com.utils.ClassDescription;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class RemoveButtonEvent implements ButtonEvent {
    public void onClick(Stage parentStage, Class<?> selectedClass, ListView<ClassDescription> objectListView) {
        boolean is_Removed = objectListView.getItems().remove(objectListView.getSelectionModel().getSelectedItem());
        if (!is_Removed) {
            new ShowMessage(parentStage, "Item wasn't selected");
        }
    }
}
