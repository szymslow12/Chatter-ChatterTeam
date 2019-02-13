package com.codecool.chatter.controller;

import com.codecool.chatter.ChatterClient;
import com.codecool.chatter.model.Connection;
import com.codecool.chatter.model.Room;
import com.codecool.chatter.model.User;
import com.codecool.chatter.view.AppView;
import com.codecool.chatter.view.RoomView;

import java.io.IOException;

public class RoomController {

    private Connection connection;
    private RoomView roomView;

    public RoomController(Connection connection) {
        this.connection = connection;
        this.roomView = new RoomView(ChatterClient.WIDTH, ChatterClient.HEIGHT);
    }


    public void run(AppView appView, User client) {
        try {
            Room room = (Room) connection.read().getObject();
            room.getChat().setClient(client);
            roomView.renderRoomView(room);
            appView.getChildren().add(appView);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
