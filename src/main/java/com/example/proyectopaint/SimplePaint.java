package com.example.proyectopaint;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SimplePaint extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Cargar la vista desde el archivo FXML
        FXMLLoader fxmlLoader = new FXMLLoader(SimplePaint.class.getResource("paint-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Simple Paint");
        stage.setResizable(false);
        stage.show();
    }
}
