package com.codecool.chatter.controller;

import com.codecool.chatter.model.Connection;
import com.codecool.chatter.model.ObjectWrapper;
import com.codecool.chatter.model.User;
import com.codecool.chatter.view.LoginView;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;

public class LoginController {

    private Connection connection;
    private LoginView loginView;
    private User client;

    private EventHandler<MouseEvent> logIn = e -> {
        try {
            TextInputControl textInputControl = loginView.getInputField().getTextInputControl();
            String nickname = textInputControl.getText();
            connection.write(new ObjectWrapper("login", nickname));
            boolean isNicknameAvailable = (Boolean) connection.read().getObject();
            checkIfNicknameIsAvailable(isNicknameAvailable, nickname);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    };


    private void checkIfNicknameIsAvailable(boolean isNicknameAvailable, String nickname) {
        Canvas badLoginTry = loginView.getAlertMessage();
        boolean isAlreadyDisplayBadLoginTry = loginView.getChildren().contains(badLoginTry);
        if (isNicknameAvailable) {
            client = new User(nickname);
            if (isAlreadyDisplayBadLoginTry) {
                loginView.getChildren().remove(badLoginTry);
            }
            ((Stage) loginView.getButtonView().getScene().getWindow()).close();
        } else {
            if (!isAlreadyDisplayBadLoginTry) {
                loginView.getChildren().add(badLoginTry);
            }
        }
    }

    public LoginController() {
        int width = 750;
        int height = (int) 400d * 2 / 3;
        this.loginView = new LoginView(width, height, 25, logIn);
    }


    public LoginController(Connection connection) {
        this();
        this.connection = connection;
    }


    public User getClient() {
        return client;
    }


    public LoginView getLoginView() {
        return loginView;
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
