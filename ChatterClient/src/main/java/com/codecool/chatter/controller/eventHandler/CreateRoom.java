package com.codecool.chatter.controller.eventHandler;

import com.codecool.chatter.model.Connection;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class CreateRoom implements EventHandler<MouseEvent> {

    private Connection connection;

    public CreateRoom(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

    }
}
