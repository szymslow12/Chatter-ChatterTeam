package com.codecool.chatter.controller.eventHandler;

import com.codecool.chatter.controller.LoginController;
import com.codecool.chatter.model.Connection;
import com.codecool.chatter.model.ObjectWrapper;
import com.codecool.chatter.model.User;
import com.codecool.chatter.view.LoginView;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LogIn implements EventHandler<InputEvent> {

    private LoginController loginController;

    public LogIn(LoginController loginController) {
        this.loginController = loginController;
    }

    @Override
    public void handle(InputEvent mouseEvent) {
        Connection connection = loginController.getConnection();
        LoginView loginView = loginController.getLoginView();
        try {
            TextInputControl textInputControl = loginView.getInputField().getTextInputControl();
            String nickname = textInputControl.getText();
            connection.write(new ObjectWrapper("login", nickname));
            boolean isNicknameAvailable = (Boolean) connection.read().getObject();
            checkIfNicknameIsAvailable(isNicknameAvailable, nickname, loginView);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    };


    private void checkIfNicknameIsAvailable(boolean isNicknameAvailable, String nickname, LoginView loginView) {
        Canvas badLoginTry = loginView.getAlertMessage();
        boolean isAlreadyDisplayBadLoginTry = loginView.getChildren().contains(badLoginTry);
        if (isNicknameAvailable) {
            loginController.setClient(new User(nickname));
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
}
