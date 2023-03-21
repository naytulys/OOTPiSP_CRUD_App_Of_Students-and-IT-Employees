package com.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.util.Map;

public class PieDiagramGenerator extends VBox {

    PieDiagramGenerator(ListView<ClassDescription> objectListView, Map<Class<?>, ClassDescription> from_Class_To_Classdescription_Map){
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
        this.getChildren().addAll(pie_Diagram);
    }
}
