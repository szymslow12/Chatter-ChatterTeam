package com.codecool.chatter.controller.eventHandler;

import com.codecool.chatter.controller.Controller;
import com.codecool.chatter.model.Connection;
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
        // TODO send message to server that user has back to lobby
        System.out.println("Exiting room=" + roomController.getControlType().getName() + "...");
        Connection connection = roomController.getConnection();
        try {
            Connection.waitForAccess(connection);
            connection.setAvailable(false);
            roomController.getUpdater().setRunning(false);
            roomController.setControlType(null);
            connection.setAvailable(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
