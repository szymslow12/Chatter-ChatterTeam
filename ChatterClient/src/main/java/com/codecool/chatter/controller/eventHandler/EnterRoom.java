package com.codecool.chatter.controller.eventHandler;

import com.codecool.chatter.model.Connection;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class EnterRoom implements EventHandler<MouseEvent> {

    private Connection connection;

    public EnterRoom(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

    }
}
