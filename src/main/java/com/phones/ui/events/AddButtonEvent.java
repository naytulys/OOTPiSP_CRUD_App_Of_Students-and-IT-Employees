package com.phones.ui.events;

import com.phones.utils.ClassDescription;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;

public class AddButtonEvent {
    public void onClick(Stage parentStage, Class<?> selectedClass, ListView<ClassDescription> objectListView) {
        try {
            Object selectClassObject = selectedClass.getConstructor().newInstance();
            ClassDescription newClassObject = new ClassDescription(selectClassObject);
            objectListView.getItems().add(newClassObject);
            // open edit window
        }  catch (NullPointerException e) {
            // show error message
        } catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
