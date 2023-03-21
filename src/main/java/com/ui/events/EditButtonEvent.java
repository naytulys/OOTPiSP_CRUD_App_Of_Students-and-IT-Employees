package com.ui.events;

import com.utils.ClassDescription;
import com.utils.EditWindow;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class EditButtonEvent implements ButtonEvent {
    public void onClick(Stage parentStage, Class<?> selectedClass, ListView<ClassDescription> objectListView) {
        ClassDescription selectedItem = objectListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            new EditWindow(parentStage, selectedItem, objectListView);
        } else {
            new ShowMessage(parentStage, "Item wasn't selected");
        }
    }
}
