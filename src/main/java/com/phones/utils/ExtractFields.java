package com.phones.utils;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
        ArrayList<FieldOptions> fields = new ArrayList<>();
        ArrayList<Node> generatedFields = new ArrayList<>();
        for (FieldOptions field : fields) {
            if (field.get_Field_User_Interface_Type() != FieldOptions.FieldType.INNER_CLASS) {
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
        Pane generatedField;
        borderPane.setRight(generatedField);
        BorderPane.setMargin(textLabel, elementInsets);
        BorderPane.setMargin(generatedField, elementInsets);
        return borderPane;
    }

}
