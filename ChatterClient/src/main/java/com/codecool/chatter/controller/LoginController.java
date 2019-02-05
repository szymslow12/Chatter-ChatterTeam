package com.codecool.chatter.controller;

import com.codecool.chatter.view.LoginView;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.Socket;

public class LoginController {

    private Socket connection;
    private LoginView loginView;

    public LoginController() {
        this.loginView = new LoginView();
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
        LoginController loginController = new LoginController();
        LoginView loginView = loginController.getLoginView();
        Stage stage = new Stage();
        stage.setAlwaysOnTop(true);
        stage.setScene(new Scene(loginView));
        stage.setTitle("Login");
        stage.setMinWidth(500d);
        stage.setMinHeight(500d);
        stage.initStyle(StageStyle.UNDECORATED);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() / 2) - stage.getMinWidth() * 0.75);
        stage.setY((primScreenBounds.getHeight() / 2) - stage.getMinHeight() * 0.5);
        stage.show();
    }
}
