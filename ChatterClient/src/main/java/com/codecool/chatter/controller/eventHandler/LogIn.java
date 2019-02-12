package com.codecool.chatter.controller.eventHandler;

import com.codecool.chatter.model.Connection;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class LogIn implements EventHandler<MouseEvent> {

    private Connection connection;

    public LogIn(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

    }
}
