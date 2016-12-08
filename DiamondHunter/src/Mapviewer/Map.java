package Mapviewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.*;


public class Map extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent content = FXMLLoader.load(getClass().getClassLoader().getResource("Mapviewer/editor.fxml"));
            Scene scene = new Scene(content, 640, 760);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
