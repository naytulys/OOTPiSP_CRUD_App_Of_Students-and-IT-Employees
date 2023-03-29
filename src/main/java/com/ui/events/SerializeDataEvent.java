package com.ui.events;

import com.utils.ClassDescription;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class SerializeDataEvent implements ButtonEvent {
    public void onClick(Stage parentStage, Class<?> selectedClass, ListView<ClassDescription> objectListView) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Resource File");
        File selectedFile = fileChooser.showOpenDialog(parentStage);
        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            String fileExtension = filePath.substring(filePath.lastIndexOf('.') + 1);
        }
    }
}
