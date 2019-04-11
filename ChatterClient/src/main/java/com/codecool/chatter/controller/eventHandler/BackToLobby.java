package com.codecool.chatter.controller.eventHandler;

import com.codecool.chatter.controller.RoomController;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;

public class BackToLobby implements EventHandler<InputEvent> {

    private RoomController roomController;

    public BackToLobby(RoomController roomController) {
        this.roomController = roomController;
    }


    @Override
    public void handle(InputEvent mouseEvent) {
        roomController.setRoom(null);
        System.out.println("Exiting Room...");
    }
}
