package com.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Map;

public class PieDiagramWindow extends Stage {

    public PieDiagramWindow(Stage parentStage, ListView<ClassDescription> objectListView, Map<Class<?>, ClassDescription> from_Class_To_Classdescription_Map) {
        initOwner(parentStage);
        initModality(Modality.APPLICATION_MODAL);
        VBox container = new VBox();
        Label classTitle = new Label("PIE DIAGRAM");
        classTitle.setFont(new Font("System Bold", 18));
        classTitle.setPadding(new Insets(15));
        container.getChildren().addAll(classTitle, new Separator());

        VBox diagram = getPieDiagram(objectListView, from_Class_To_Classdescription_Map);
        container.getChildren().add(diagram);
        HBox bottomNavigation = new HBox();
        Button addButton = new Button("Apply");
        addButton.setPadding(new Insets(5, 10, 10, 5));
        addButton.setOnAction(actionEvent -> this.close());

        HBox.setMargin(addButton, new Insets(10));
        bottomNavigation.getChildren().add(addButton);

        container.getChildren().add(bottomNavigation);

        Scene scene = new Scene(container);
        setScene(scene);

        showAndWait();
    }

    private VBox getPieDiagram(ListView<ClassDescription> objectListView, Map<Class<?>, ClassDescription> from_Class_To_Classdescription_Map){
        VBox vBox = new VBox();
        PieChart pie_Diagram = new PieChart();
        for (ClassDescription currentDescription : objectListView.getItems()) {
            if (from_Class_To_Classdescription_Map.containsKey(currentDescription.getSelected_Class())) {
                ClassDescription currentEntry = from_Class_To_Classdescription_Map.get(currentDescription.getSelected_Class());
                int value = currentEntry.getClass_Instance_Count() + 1;
                currentEntry.setClass_Instance_Count(value);
                from_Class_To_Classdescription_Map.put(currentDescription.getSelected_Class(), currentEntry);
            }
        }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Class<?> currentClass : from_Class_To_Classdescription_Map.keySet()){
            if (from_Class_To_Classdescription_Map.containsKey(currentClass)) {
                ClassDescription currentEntry = from_Class_To_Classdescription_Map.get(currentClass);
                pieChartData.add(new PieChart.Data(currentEntry.getLocalName(), currentEntry.getClass_Instance_Count()));
            }
        }
        pie_Diagram.setData(pieChartData);
        vBox.getChildren().addAll(pie_Diagram);
        return vBox;
    }
}
