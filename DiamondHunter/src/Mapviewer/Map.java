package Mapviewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
public class Map extends Application {
        @Override
        public void start(Stage primaryStage) {
            try {
                AnchorPane root = new AnchorPane();
                Scene scene = new Scene(root,640,640);
                MainCanvas mainCanvas = new MainCanvas(640, 640);
                root.getChildren().add(mainCanvas);
                Parent content = FXMLLoader.load(getClass().getClassLoader().getResource("edit.fxml"));
                //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
