package Mapviewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Map extends Application {
        @Override
        public void start(Stage primaryStage) {
            try {
                BorderPane root = new BorderPane();
                Scene scene = new Scene(root,640,720);
                MainCanvas mainCanvas = new MainCanvas(640, 720);
                root.getChildren().add(mainCanvas);
                Parent content = FXMLLoader.load(getClass().getClassLoader().getResource("Mapviewer/editor.fxml"));
                //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                root.setCenter(content);
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
