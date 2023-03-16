package com.phones.ui;

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

        ObservableList<ClassDescription> classList = getClassList();
        primaryStage.setTitle("Communication devices editor");

        ListView<ClassDescription> objectListView = new ListView<>();
        objectListView.setOrientation(Orientation.VERTICAL);

        Label labelSelectClass = new Label("Select class: ");
        labelSelectClass.setPadding(new Insets(15));

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

        bottomNavigation.setSpacing(5);

        addButton.setPadding(buttonPadding);
        editButton.setPadding(buttonPadding);
        removeButton.setPadding(buttonPadding);

        HBox.setMargin(addButton, buttonMargin);
        HBox.setMargin(editButton, buttonMargin);
        HBox.setMargin(removeButton, buttonMargin);

        bottomNavigation.getChildren().addAll(addButton, editButton, removeButton);

        VBox vBox = new VBox();
        vBox.getChildren().add(topNavigation);
        vBox.getChildren().add(objectListView);
        vBox.getChildren().add(bottomNavigation);
        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

