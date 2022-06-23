package com.example.demo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    public static void display(String title , String message)
    {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(250);
        stage.setMinHeight(150);


        Label label = new Label(message);
        label.setAlignment(Pos.CENTER);
        Button bt = new Button("Ok!");
        bt.setOnAction(event -> {
            stage.close();
        });

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.getChildren().add(label);
        vbox.getChildren().add(bt);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(30);

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.getIcons().add(new Image("C:\\Users\\En.Yara\\IdeaProjects\\demo\\logo.jpeg"));
        stage.showAndWait();
    }
}
