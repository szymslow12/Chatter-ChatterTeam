package com.codecool.chatter.controller.eventHandler;

import com.codecool.chatter.controller.LobbyController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class EnterRoom implements EventHandler<MouseEvent> {

    private LobbyController lobbyController;

    public EnterRoom(LobbyController lobbyController) {
        this.lobbyController = lobbyController;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

    }
}
