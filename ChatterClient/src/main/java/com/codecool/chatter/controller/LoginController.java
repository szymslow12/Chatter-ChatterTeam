package com.codecool.chatter.controller;

import com.codecool.chatter.view.LoginView;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.Socket;

public class LoginController {

    private Socket connection;
    private LoginView loginView;

    private EventHandler<MouseEvent> joinToLobby = e -> System.out.println("Clicked");

    public LoginController() {
        this.loginView = new LoginView(750, 500, 25, joinToLobby);
    }


    public LoginController(Socket connection) {
        this();
        this.connection = connection;
    }


    //TODO
    public void run() {
        runLoginStage();
    }


    public LoginView getLoginView() {
        return loginView;
    }


    private void runLoginStage() {
        Stage stage = new Stage();
        setStageProperties(stage);
        setPositions(stage);
        stage.show();
    }


    private void setStageProperties(Stage stage) {
        stage.setAlwaysOnTop(true);
        stage.setScene(new Scene(loginView));
        stage.setTitle("Login");
        stage.setMinWidth(750);
        stage.setMinHeight(500);
        stage.initStyle(StageStyle.UNDECORATED);
    }


    private void setPositions(Stage stage) {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        double x = primScreenBounds.getMaxX();
        double y = primScreenBounds.getMaxY();
        stage.setX((x / 2) - stage.getMinWidth() * 0.5);
        stage.setY((y / 2) - stage.getMinHeight() * 0.5);
    }
}
