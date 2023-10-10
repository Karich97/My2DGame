package com.example.my2dgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class SimpleGUIGame extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGUIGame.class.getResource("my2DGame.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 325);
        stage.setResizable(false);
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
            if (e.getCode() == KeyCode.ESCAPE) {
                if (!GameController.inGame) GameController.restart = true;
                else GameController.isPaused = !GameController.isPaused;
            }
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}