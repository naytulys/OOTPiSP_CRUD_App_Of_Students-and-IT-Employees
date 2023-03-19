package com.phones.ui.events;

import com.phones.utils.ClassDescription;
import com.phones.utils.EditWindow;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;

public class AddButtonEvent implements ButtonEvent {
    public void onClick(Stage parentStage, Class<?> selectedClass, ListView<ClassDescription> objectListView) {
        try {
            Object selectClassObject = selectedClass.getConstructor().newInstance();
            ClassDescription newClassObject = new ClassDescription(selectClassObject);
            objectListView.getItems().add(newClassObject);
            // open edit window
            new EditWindow(parentStage, newClassObject, objectListView);
        }  catch (NullPointerException e) {
            new ShowMessage(parentStage, "Item wasn't selected");
        } catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
