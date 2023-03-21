package com.ui.events;

import com.Main;
import com.utils.ClassDescription;
import com.utils.PieDiagramWindow;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class ShowDiagramEvent implements ButtonEvent {

    private static final Map<Class<?>, ClassDescription> FROM_CLASS_TO_CLASSDESCRIPTION_MAP = new HashMap<>();

    static {
        for (ClassDescription current : Main.getClassList()){
            FROM_CLASS_TO_CLASSDESCRIPTION_MAP.put(current.getSelected_Class(), current);
        }
    }

    public void onClick(Stage parentStage, Class<?> selectedClass, ListView<ClassDescription> objectListView) {
        for (Class<?> currentKey : FROM_CLASS_TO_CLASSDESCRIPTION_MAP.keySet()){
            ClassDescription currentEntry = FROM_CLASS_TO_CLASSDESCRIPTION_MAP.get(currentKey);
            currentEntry.setClass_Instance_Count(0);
            FROM_CLASS_TO_CLASSDESCRIPTION_MAP.put(currentKey, currentEntry);
        }
        new PieDiagramWindow(parentStage, objectListView, FROM_CLASS_TO_CLASSDESCRIPTION_MAP);
    }
}
