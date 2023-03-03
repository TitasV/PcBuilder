package com.example.pcbuilder2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public Main() throws IOException {
    }

    public static void main(String[] args) {
        launch(args);
    }
    Parent root = FXMLLoader.load(getClass().getResource("kompiuteris.fxml"));
    @Override
    public void start(Stage primaryStage) throws IOException {

        primaryStage.setTitle("Kompiuteris");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
