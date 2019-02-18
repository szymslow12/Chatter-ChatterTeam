package com.codecool.chatter.controller;

import com.codecool.chatter.ChatterClient;
import com.codecool.chatter.controller.eventHandler.SendMessage;
import com.codecool.chatter.model.Connection;
import com.codecool.chatter.model.Room;
import com.codecool.chatter.model.User;
import com.codecool.chatter.view.AppView;
import com.codecool.chatter.view.RoomView;

import java.io.IOException;

public class RoomController {

    private Connection connection;
    private RoomView roomView;
    private User client;

    public RoomController(Connection connection, User client) {
        this.connection = connection;
        this.roomView = new RoomView(ChatterClient.WIDTH, ChatterClient.HEIGHT);
        this.client = client;
    }


    public void run(AppView appView, Room chosenRoom) {
        client.setCurrentRoomId(chosenRoom.getId());
        chosenRoom.getChat().setClient(client);
        roomView.renderRoomView(chosenRoom, new SendMessage(connection, this, client));
        appView.getChildren().add(roomView);
    }


    public void updateChat() throws IOException, ClassNotFoundException {
        Room roomToUpdate = (Room) connection.read().getObject();
        roomToUpdate.getChat().setClient(client);
        roomView.getChildren().clear();
        roomView.renderRoomView(roomToUpdate, new SendMessage(connection, this, client));
    }


    public RoomView getRoomView() {
        return roomView;
    }
}
