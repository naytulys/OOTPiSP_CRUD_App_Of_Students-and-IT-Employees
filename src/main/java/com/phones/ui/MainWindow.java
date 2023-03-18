package com.phones.ui;

import com.phones.ui.events.AddButtonEvent;
import com.phones.ui.events.EditButtonEvent;
import com.phones.ui.events.RemoveButtonEvent;
import com.phones.utils.ClassDescription;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static com.phones.Main.getClassList;


public class MainWindow extends Application {

    public static void mainWindow(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        /* get description of all app classes */
        ObservableList<ClassDescription> classList = getClassList();
        primaryStage.setTitle("Communication devices editor");
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
        * */
        addButton.setOnAction(actionEvent -> {
            new AddButtonEvent().onClick(primaryStage, selectClass.getValue().getClass(), objectListView);
        });

        editButton.setOnAction(actionEvent -> {
            new EditButtonEvent().onClick(primaryStage, selectClass.getValue().getClass(), objectListView);
        });

        removeButton.setOnAction(actionEvent -> {
            new RemoveButtonEvent().onClick(primaryStage, selectClass.getValue().getClass(), objectListView);
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

}

