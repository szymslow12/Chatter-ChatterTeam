package com.codecool.chatter.controller;

import com.codecool.chatter.controller.eventHandler.LogIn;
import com.codecool.chatter.model.Connection;
import com.codecool.chatter.model.User;
import com.codecool.chatter.view.LoginView;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController {

    private Connection connection;
    private LoginView loginView;
    private User client;


    public LoginController() {
        int width = 750;
        int height = (int) 400d * 2 / 3;
        this.loginView = new LoginView(width, height, 25, new LogIn(this));
    }


    public LoginController(Connection connection) {
        this();
        this.connection = connection;
    }


    public void setClient(User client) {
        this.client = client;
    }


    public User getClient() {
        return client;
    }


    public LoginView getLoginView() {
        return loginView;
    }


    public Connection getConnection() {
        return connection;
    }


    public void runLoginStage() {
        Stage stage = new Stage();
        setStageProperties(stage);
        setPositions(stage);
        stage.show();
    }


    private void setStageProperties(Stage stage) {
        stage.setAlwaysOnTop(true);
        stage.setScene(new Scene(loginView));
        stage.setTitle("Login");
        stage.setMinWidth(loginView.getMinWidth());
        stage.setMinHeight(loginView.getMinHeight());
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
