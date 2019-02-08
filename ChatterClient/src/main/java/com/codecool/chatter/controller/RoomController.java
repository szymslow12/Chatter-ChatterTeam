package com.codecool.chatter.controller;

import com.codecool.chatter.model.Room;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RoomController {

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public RoomController(ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) {
        this.outputStream = objectOutputStream;
        this.inputStream = objectInputStream;
    }


    public void run() {
        try {
            Room room = (Room) inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
