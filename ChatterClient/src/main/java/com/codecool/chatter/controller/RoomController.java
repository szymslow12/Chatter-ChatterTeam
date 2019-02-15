package com.codecool.chatter.controller;

import com.codecool.chatter.ChatterClient;
import com.codecool.chatter.model.Connection;
import com.codecool.chatter.model.Room;
import com.codecool.chatter.model.User;
import com.codecool.chatter.view.AppView;
import com.codecool.chatter.view.RoomView;

public class RoomController {

    private Connection connection;
    private RoomView roomView;

    public RoomController(Connection connection) {
        this.connection = connection;
        this.roomView = new RoomView(ChatterClient.WIDTH, ChatterClient.HEIGHT);
    }


    public void run(AppView appView, User client, Room chosenRoom) {
        System.out.println(chosenRoom.getName());
        chosenRoom.getChat().setClient(client);
        roomView.renderRoomView(chosenRoom);
        appView.getChildren().add(appView);
    }
}
