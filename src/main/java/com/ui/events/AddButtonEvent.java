package com.ui.events;

import com.utils.ClassDescription;
import com.utils.EditWindow;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;

public class AddButtonEvent implements ButtonEvent {
    public void onClick(Stage parentStage, Class<?> selectedClass, ListView<ClassDescription> objectListView) {
        if (selectedClass == null) {
            new ShowMessage(parentStage, "Item wasn't selected");
            return;
        }
        Object selectClassObject = null;
        try {
            selectClassObject = selectedClass.getConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        if (selectClassObject != null) {
            ClassDescription newClassObject = new ClassDescription(selectClassObject);
            objectListView.getItems().add(newClassObject);
            // open edit window
            new EditWindow(parentStage, newClassObject, objectListView);
        }
    }
}
