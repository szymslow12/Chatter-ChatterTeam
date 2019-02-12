package com.codecool.chatter.controller;

import com.codecool.chatter.model.Connection;
import com.codecool.chatter.model.Room;

import java.io.IOException;

public class RoomController {

    private Connection connection;
//    private RoomView roomView;

    public RoomController(Connection connection) {
        this.connection = connection;
    }


    public void run() {
        try {
            Room room = (Room) connection.read().getObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
