package org.hbdev;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class MainFx extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        URL fxmlUrl = getClass().getResource("/views/main.fxml");
        System.out.println("FXML file URL: " + fxmlUrl); // Debug print
        if (fxmlUrl == null) {
            throw new RuntimeException("FXML file not found!");
        }
        Parent root = FXMLLoader.load(fxmlUrl);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("FXML + Scene Builder Example");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
