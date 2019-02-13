package com.codecool.chatter.controller;

import com.codecool.chatter.model.Connection;
import com.codecool.chatter.model.Room;
import com.codecool.chatter.model.User;
import com.codecool.chatter.view.AppView;

import java.io.IOException;

public class RoomController {

    private Connection connection;
//    private RoomView roomView;

    public RoomController(Connection connection) {
        this.connection = connection;
    }


    public void run(AppView appView, User client) {
        try {
            Room room = (Room) connection.read().getObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
