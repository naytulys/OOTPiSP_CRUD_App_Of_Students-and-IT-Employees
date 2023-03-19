package com.phones.ui.events;

import com.phones.utils.ClassDescription;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public interface ButtonEvent {
    void onClick(Stage parentStage, Class<?> selectedClass, ListView<ClassDescription> objectListView);
}
