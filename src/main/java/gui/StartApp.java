package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartApp extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader openLoader = new FXMLLoader(StartApp.class.getResource("Process.fxml"));
        Scene scene = new Scene(openLoader.load(), 700, 600);
        stage.setTitle("gabella.prod");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}