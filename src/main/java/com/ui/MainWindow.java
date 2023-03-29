package com.ui;

import com.Main;
import com.entities.*;
import com.ui.events.*;
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

import java.util.ArrayList;
import java.util.Arrays;


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
        Button diagramButton = new Button("Diagram");
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

        diagramButton.setOnAction(actionEvent -> {
            execute_ButtonEvent(new ShowDiagramEvent(), primaryStage, selectClass, objectListView);
        });

        bottomNavigation.setSpacing(5);

        addButton.setPadding(buttonPadding);
        editButton.setPadding(buttonPadding);
        removeButton.setPadding(buttonPadding);
        diagramButton.setPadding(buttonMargin);

        HBox.setMargin(addButton, buttonMargin);
        HBox.setMargin(editButton, buttonMargin);
        HBox.setMargin(removeButton, buttonMargin);
        HBox.setMargin(diagramButton, buttonMargin);

        bottomNavigation.getChildren().addAll(addButton, editButton, removeButton, diagramButton);
        /* united all window components */
        VBox vBox = new VBox();
        vBox.getChildren().add(topNavigation);
        vBox.getChildren().add(objectListView);
        vBox.getChildren().add(bottomNavigation);
        /* initialize view table*/
        initialize_CRUD_App_Table(objectListView);
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

    private void initialize_CRUD_App_Table(ListView<ClassDescription> objectListView) {
        ArrayList<ClassDescription> classDescriptions = new ArrayList<>(
                Arrays.asList(
                        new ClassDescription(new Student("Aleksey", "Bobrik", Person.Sex.Male, "bobrik@gmail.com", "BSUIR", "KSIS", "POIT", 2, 151002, false, true)),
                        new ClassDescription(new Manager("Vadim", "Kragel", Person.Sex.Male, "Kragel@gmail.com", 6000, 20, Employee.Education.Height, 1000)),
                        new ClassDescription(new Tester("Daria", "Zavaluk", Person.Sex.Female, "zavaluk@gmail.com", 1000, 3, Employee.Education.None, Tester.TestType.Automated)),
                        new ClassDescription(new Designer("Alena", "Kruegar", Person.Sex.Female, "kruegar@gmail.com", 1500, 6, Employee.Education.Middle, Designer.Designer_Skills.Illustrator, Designer.Designer_Type.Game)),
                        new ClassDescription(new Programmer("Nikita", "Krashevski", Person.Sex.Male, "nirita.krashevski@gmail.com", 5000, 13, Employee.Education.Height, Programmer.Category.Senior, Programmer.ProgSkills.JAVA))
                )
        );
        objectListView.getItems().addAll(classDescriptions);
    }

}


