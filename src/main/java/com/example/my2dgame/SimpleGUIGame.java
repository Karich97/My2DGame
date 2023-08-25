package com.example.my2dgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SimpleGUIGame extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGUIGame.class.getResource("my2DGame.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 299);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT) GameController.right = true;
            if (e.getCode() == KeyCode.A || e.getCode() == KeyCode.LEFT) GameController.left = true;
            if ((e.getCode() == KeyCode.SPACE
                    || e.getCode() == KeyCode.UP
                    || e.getCode() == KeyCode.W)
                    && !GameController.jump && GameController.readyToJump) GameController.jump = true;
        });
        scene.setOnKeyReleased(e ->{
            if (e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT) GameController.right = false;
            if (e.getCode() == KeyCode.A || e.getCode() == KeyCode.LEFT) GameController.left = false;
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}