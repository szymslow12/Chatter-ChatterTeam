package com.codecool.chatter.controller.eventHandler;

import com.codecool.chatter.controller.LoginController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class LogIn implements EventHandler<MouseEvent> {

    private LoginController loginController;

    public LogIn(LoginController loginController) {
        this.loginController = loginController;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

    }
}
