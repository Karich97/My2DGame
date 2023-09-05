package com.example.my2dgame;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class GameController {
    ParallelTransition parallelTransition;
    TranslateTransition enemyTransition;
    static  boolean left = false, right = false, jump = false, readyToJump = true, isPaused = false, inGame = true, restart = false;
    private final int playerDefaultSpeed = 3, jumpDefaultSpeed = 5, BG_WIDTH = 600;
    private int playerSpeed = playerDefaultSpeed, jumpDownSpeed = jumpDefaultSpeed;

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if (jump && player.getLayoutY() > 90f) {
                readyToJump = false;
                player.setLayoutY(player.getLayoutY() - jumpDownSpeed);
            }
            else if (player.getLayoutY() <= 181f) {
                jump = false;
                player.setLayoutY(player.getLayoutY() + jumpDownSpeed);
                if (player.getLayoutY() >= 180f) readyToJump = true;
            }
            if (right && player.getLayoutX() < 100f) player.setLayoutX(player.getLayoutX() + playerSpeed);
            if (left && player.getLayoutX() > 28f) player.setLayoutX(player.getLayoutX() - playerSpeed);
            if (isPaused && !labelPause.isVisible()) {
                playerSpeed = 0;
                jumpDownSpeed = 0;
                parallelTransition.pause();
                enemyTransition.pause();
                labelPause.setVisible(true);
            }
            else if (!isPaused && labelPause.isVisible()) {
                playerSpeed = playerDefaultSpeed;
                jumpDownSpeed = jumpDefaultSpeed;
                parallelTransition.play();
                enemyTransition.play();
                labelPause.setVisible(false);
            }

            if (player.getBoundsInParent().intersects(enemy.getBoundsInParent()) && inGame){
                labelLose.setVisible(true);
                playerSpeed = 0;
                jumpDownSpeed = 0;
                parallelTransition.pause();
                enemyTransition.pause();
                inGame = false;
            }
            else if (restart){
                playerSpeed = playerDefaultSpeed;
                jumpDownSpeed = jumpDefaultSpeed;
                inGame = true;
                restart = false;
                labelLose.setVisible(false);

                enemyTransition.stop();
                startNewEnemy(enemy);
                enemyTransition.play();
                parallelTransition.play();
            }
        }
    };

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView bg1, bg2, player, enemy;

    @FXML
    private Label labelPause, labelLose;

    @FXML
    void initialize() {
        TranslateTransition bgOneTransition = new TranslateTransition(Duration.millis(5000), bg1);
        bgOneTransition.setFromX(0);
        bgOneTransition.setToX(BG_WIDTH * -1);
        bgOneTransition.setInterpolator(Interpolator.LINEAR);

        TranslateTransition bgTwoTransition = new TranslateTransition(Duration.millis(5000), bg2);
        bgTwoTransition.setFromX(0);
        bgTwoTransition.setToX(BG_WIDTH * -1);
        bgTwoTransition.setInterpolator(Interpolator.LINEAR);

        startNewEnemy(enemy);

        parallelTransition = new ParallelTransition(bgOneTransition, bgTwoTransition);
        parallelTransition.setCycleCount(Animation.INDEFINITE);
        parallelTransition.play();

        timer.start();
    }

    private void startNewEnemy(ImageView enemy){
        enemyTransition = new TranslateTransition(Duration.millis(3500), enemy);
        enemyTransition.setFromX(0);
        enemyTransition.setToX(BG_WIDTH * -1 - 100);
        enemyTransition.setInterpolator(Interpolator.LINEAR);
        enemyTransition.setCycleCount(Animation.INDEFINITE);
        enemyTransition.play();
    }

}
