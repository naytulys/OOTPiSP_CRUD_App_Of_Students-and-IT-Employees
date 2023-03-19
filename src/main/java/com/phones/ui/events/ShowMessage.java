package com.phones.ui.events;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ShowMessage extends Stage {
    public ShowMessage(Stage parentStage, String message) {
        initOwner(parentStage);
        initModality(Modality.APPLICATION_MODAL);

        VBox container = new VBox();

        BorderPane messageBox = new BorderPane();
        Label messageLabel = new Label(message);
        messageBox.setCenter(messageLabel);
        BorderPane.setMargin(messageLabel, new Insets(30));

        Button closeButton = new Button("Close");
        closeButton.setPadding(new Insets(5));
        closeButton.setOnAction(actionEvent -> this.close());
        VBox.setMargin(closeButton, new Insets(10));
        container.getChildren().addAll(messageBox, closeButton);
        Scene scene = new Scene(container);
        setScene(scene);

        showAndWait();
    }
}
