package com.codecool.chatter.controller;

import com.codecool.chatter.model.User;
import com.codecool.chatter.view.LoginView;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class LoginController {

    private Socket connection;
    private LoginView loginView;
    private User client;

    private EventHandler<MouseEvent> logIn = e -> {
        try {
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            DataInputStream inputStream = new DataInputStream(connection.getInputStream());
            TextInputControl textInputControl = loginView.getInputField().getTextInputControl();
            String nickname = textInputControl.getText();
            outputStream.writeUTF(nickname);
            boolean isNicknameAvailable = true;
            if (isNicknameAvailable) {
                client = new User(nickname);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    };

    public LoginController() {
        int width = 750;
        int height = (int) 400d * 2 / 3;
        this.loginView = new LoginView(width, height, 25, logIn);
    }


    public LoginController(Socket connection) {
        this();
        this.connection = connection;
    }


    public User getClient() {
        return client;
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
