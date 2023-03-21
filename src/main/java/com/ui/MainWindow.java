package com.ui;

import com.ui.events.AddButtonEvent;
import com.ui.events.ButtonEvent;
import com.ui.events.EditButtonEvent;
import com.ui.events.RemoveButtonEvent;
import com.utils.ClassDescription;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.Main;


public class MainWindow extends Application {

    public static void mainWindow(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        /* get description of all app classes */
        ObservableList<ClassDescription> classList = Main.getClassList();
        primaryStage.setTitle("Students-and-IT-Employees");
        // define editor table of classes description
        ListView<ClassDescription> objectListView = new ListView<>();
        objectListView.setOrientation(Orientation.VERTICAL);

        Label labelSelectClass = new Label("Select class: ");
        labelSelectClass.setPadding(new Insets(15));
        /* define choice box that contains from app classes description */
        ChoiceBox<ClassDescription> selectClass = new ChoiceBox<>(classList);

        BorderPane topNavigation = new BorderPane();
        topNavigation.setLeft(labelSelectClass);
        topNavigation.setCenter(selectClass);

        HBox bottomNavigation = new HBox();
        Button addButton = new Button("Add");
        Button editButton = new Button("Edit");
        Button removeButton = new Button("Remove");
        Insets buttonMargin = new Insets(15);
        Insets buttonPadding = new Insets(10, 20, 10, 20);
        /*
         * set onClick Event for Buttons
         * Add execute method for add, edit and remove buttons
         * in choice at program starting selected value equal null
         * that why all event listener init with selected value equal null
         * and all time you see when click on button error message
         * which tell that item is not selected
         * but now execute method at all clicking on buttons
         * try to get value from choice box
         * */
        addButton.setOnAction(actionEvent -> {
            execute_ButtonEvent(new AddButtonEvent(), primaryStage, selectClass, objectListView);
        });

        editButton.setOnAction(actionEvent -> {
            execute_ButtonEvent(new EditButtonEvent(), primaryStage, selectClass, objectListView);
        });

        removeButton.setOnAction(actionEvent -> {
            execute_ButtonEvent(new RemoveButtonEvent(), primaryStage, selectClass, objectListView);
        });

        bottomNavigation.setSpacing(5);

        addButton.setPadding(buttonPadding);
        editButton.setPadding(buttonPadding);
        removeButton.setPadding(buttonPadding);

        HBox.setMargin(addButton, buttonMargin);
        HBox.setMargin(editButton, buttonMargin);
        HBox.setMargin(removeButton, buttonMargin);

        bottomNavigation.getChildren().addAll(addButton, editButton, removeButton);
        /* united all window components */
        VBox vBox = new VBox();
        vBox.getChildren().add(topNavigation);
        vBox.getChildren().add(objectListView);
        vBox.getChildren().add(bottomNavigation);
        /* view window */
        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void execute_ButtonEvent(ButtonEvent ButtonEvent, Stage primaryStage,
                                     ChoiceBox<ClassDescription> selectedClass,
                                     ListView<ClassDescription> objectListView) {
        Class<?> selected_Class_Value = null;
        if (selectedClass.getValue() != null) {
            selected_Class_Value = selectedClass.getValue().getSelected_Class();
        }
        ButtonEvent.onClick(primaryStage, selected_Class_Value, objectListView);
    }

}

