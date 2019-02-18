package com.codecool.chatter.controller;

import com.codecool.chatter.ChatterClient;
import com.codecool.chatter.controller.eventHandler.SendMessage;
import com.codecool.chatter.model.Connection;
import com.codecool.chatter.model.Room;
import com.codecool.chatter.model.User;
import com.codecool.chatter.view.AppView;
import com.codecool.chatter.view.RoomView;

public class RoomController {

    private Connection connection;
    private RoomView roomView;
    private Room room;

    public RoomController(Connection connection, Room room) {
        this.connection = connection;
        this.roomView = new RoomView(ChatterClient.WIDTH, ChatterClient.HEIGHT);
        this.room = room;
    }


    public void run(AppView appView, User client) {
        client.setCurrentRoomId(room.getId());
        room.getChat().setClient(client);
        roomView.renderRoomView(room, new SendMessage(connection, this, client));
        appView.getChildren().add(roomView);
    }


    public void updateChat() {
        roomView.clearChildren();
    }


    public RoomView getRoomView() {
        return roomView;
    }

    public Room getRoom() {
        return room;
    }
}
