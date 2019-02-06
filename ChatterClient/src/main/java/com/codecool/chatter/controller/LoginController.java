package com.codecool.chatter.controller;

import com.codecool.chatter.model.User;
import com.codecool.chatter.view.LoginView;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
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
            outputStream.flush();
            boolean isNicknameAvailable = inputStream.readBoolean();
            if (isNicknameAvailable) {
                client = new User(nickname);
            } else {
                loginView.getChildren().add(new Canvas(loginView.getMinWidth(), loginView.getMinHeight()) {
                    private void drawBadLoginTry(Canvas canvas) {
                        GraphicsContext context = canvas.getGraphicsContext2D();
                        double width = loginView.getInputField().getWidth();
                        double height = loginView.getInputField().getHeight() / 3;
                        double x = loginView.getMinWidth() - width;
                        context.strokeRect(x / 2, 5, width, height);
                        context.setFill(Color.web("#ffffff"));
                        context.fillRect((x / 2 ) + 1, 5, width - 1, height - 1);
                    }
                    {
                        drawBadLoginTry(this);
                    }
                });
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
