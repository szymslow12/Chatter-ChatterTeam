package com.codecool.chatter.controller.eventHandler;

import com.codecool.chatter.controller.Controller;
import com.codecool.chatter.model.Room;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;

public class BackToLobby implements EventHandler<InputEvent> {

    private Controller<Room> roomController;

    public BackToLobby(Controller<Room> roomController) {
        this.roomController = roomController;
    }


    @Override
    public void handle(InputEvent mouseEvent) {
        System.out.println("Exiting room=" + roomController.getControlType().getName() + "...");
        roomController.setControlType(null);
    }
}
