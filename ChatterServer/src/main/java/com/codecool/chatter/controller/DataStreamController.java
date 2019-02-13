package com.codecool.chatter.controller;

import com.codecool.chatter.model.Chat;
import com.codecool.chatter.model.ClientInfo;
import com.codecool.chatter.model.Room;
import com.codecool.chatter.model.User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class DataStreamController extends Thread {

    private AppController appController;

    public DataStreamController(AppController appController) {
        this.appController = appController;
    }

    public void run() {

        List<ClientInfo> clients;
        User user;
        ObjectOutputStream out;

        try {

            while (true) {
                clients = appController.getClientInfo();
                for (ClientInfo client : clients) {
                    out = client.getOutputStream();
                    user = client.getUser();
                    if (user.getCurrentRoomId().equals(null)) {
                        out.writeObject(appController.wrapObject("lobby", appController.getLobby()));
                        out.flush();
                        break;

                    } else if (!user.getCurrentRoomId().equals(null)) {
                        Room room = appController.getRoomById(user.getCurrentRoomId());
                        Object roomUsers = appController.wrapObject("roomUsers", room.getUsers());
                        Chat chat = room.getChat();
                        out.writeObject(roomUsers);
                        out.writeObject(chat);
                        out.flush();
                    }
                }

                Thread.sleep(100);
            }
        }catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }


}
