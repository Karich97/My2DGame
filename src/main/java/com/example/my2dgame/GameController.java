package com.example.my2dgame;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class GameController {
    static  boolean left = false;
    static boolean right = false;
    static boolean jump = false;
    static boolean readyToJump = true;
    private final int playerSpeed = 3, jumpDownSpeed = 5;

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
        }
    };

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView bg1, bg2, player;

    @FXML
    void initialize() {
        TranslateTransition bgOneTransition = new TranslateTransition(Duration.millis(5000), bg1);
        bgOneTransition.setFromX(0);
        int BG_WIDTH = 600;
        bgOneTransition.setToX(BG_WIDTH * -1);
        bgOneTransition.setInterpolator(Interpolator.LINEAR);

        TranslateTransition bgTwoTransition = new TranslateTransition(Duration.millis(5000), bg2);
        bgTwoTransition.setFromX(0);
        bgTwoTransition.setToX(BG_WIDTH * -1);
        bgTwoTransition.setInterpolator(Interpolator.LINEAR);

        ParallelTransition parallelTransition = new ParallelTransition(bgOneTransition, bgTwoTransition);
        parallelTransition.setCycleCount(Animation.INDEFINITE);
        parallelTransition.play();

        timer.start();
    }

}
