package com.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ExtractFields extends VBox {
    private final Stage stage;
    private final ListView<ClassDescription> objectListView;

    public ExtractFields(Stage stage, Object objectToParse, ListView<ClassDescription> objectListView) {
        this.stage = stage;
        this.objectListView = objectListView;
        getChildren().addAll(generateFields(objectToParse));
    }

    private ArrayList<Node> generateFields(Object objectToParse) {
        /*
         * call some parse methods in order to get
         * description of fields assigned to object
         * */
        ArrayList<FieldOptions> fields = FieldsParser.parseFields(objectToParse);
        ArrayList<Node> generatedFields = new ArrayList<>();
        for (FieldOptions field : fields) {
            if (field.getFieldUserInterfaceType() == FieldOptions.FieldType.INNERCLASS) {
                generatedFields.add(new Separator());
                Label titleLabel = new Label(field.getFieldName());

                titleLabel.setPadding(new Insets(10, 15, 15, 5));
                titleLabel.setFont(new Font("System Bold", 18));

                generatedFields.add(titleLabel);
                generatedFields.add(generateInnerClassField(objectToParse, field));
                generatedFields.add(new Separator());
            } else {
                generatedFields.add(generatePrimitiveField(objectToParse, field));
            }
        }
        return generatedFields;
    }

    private Node generatePrimitiveField(Object objectToInspect, FieldOptions field) {
        BorderPane borderPane = new BorderPane();
        Insets elementInsets = new Insets(10, 15, 15, 10);
        Label textLabel = new Label(field.getFieldName());
        borderPane.setLeft(textLabel);
        /*
         * call methods to generate object fields
         * */
        Pane generatedField = new FieldGenerator(objectToInspect, field);
        borderPane.setRight(generatedField);
        BorderPane.setMargin(textLabel, elementInsets);
        BorderPane.setMargin(generatedField, elementInsets);
        return borderPane;
    }

    private Node generateInnerClassField(Object objectToInspect, FieldOptions field) {
        BorderPane borderPane = new BorderPane();
        Insets elementInsets = new Insets(10, 15, 15, 10);
        ObservableList<ClassDescription> comboBoxValues = FXCollections.observableArrayList();
        for (ClassDescription objectValue : objectListView.getItems()) {
            if (objectValue.getSelected_Class() == field.getFieldClassType()) {
                comboBoxValues.add(objectValue);
            }
        }
        ComboBox<ClassDescription> comboBox = new ComboBox<>(comboBoxValues);
        ClassDescription selectedDefaultOption = null;
        try {
            Object objectToFind = field.getGet().invoke(objectToInspect);
            for (ClassDescription objectValue : comboBoxValues) {
                if (objectValue.getObject_For_Description() == objectToFind) {
                    selectedDefaultOption = objectValue;
                }
            }
            comboBox.getSelectionModel().select(selectedDefaultOption);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        comboBox.setOnAction(actionEvent -> {
            ClassDescription selectedItem = comboBox.getSelectionModel().getSelectedItem();
            try {
                field.getSet().invoke(objectToInspect, selectedItem.getObject_For_Description());
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        BorderPane.setMargin(comboBox, elementInsets);
        borderPane.setLeft(comboBox);

        Button editInnerClassButton = new Button("Edit inner class");
        editInnerClassButton.setOnAction(actionEvent -> {
            try {
                Object innerObject = field.getGet().invoke(objectToInspect);
                if (innerObject != null) {
                    ClassDescription objectToShow = new ClassDescription(innerObject);
                    new EditWindow(stage, objectToShow, objectListView);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        BorderPane.setMargin(editInnerClassButton, elementInsets);
        borderPane.setRight(editInnerClassButton);

        return borderPane;
    }

}
