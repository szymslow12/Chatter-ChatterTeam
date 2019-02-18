package com.codecool.chatter.controller;

import com.codecool.chatter.model.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
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
                        out.writeObject(roomUsers);
                        out.flush();
                        List<Message> messages = room.getChat().getMessages();
                        List<Message> messageToSend = getMessageToSend(client, room);
                        Object newMessages = appController.wrapObject("updateChat", messageToSend);
                        out.writeObject(newMessages);
                        out.flush();
                    }
                }

                Thread.sleep(100);
            }
        }catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    private List<Message> getMessageToSend(ClientInfo client, Room room) {
        int latestMsgIndex = client.getLatestMsgIndex();
        List<Message> messages = room.getChat().getMessages();
        List<Message> latestMsg = new ArrayList<>();
        for(int i = latestMsgIndex; i < messages.size(); i++) {
            latestMsg.add(messages.get(i));
        }
        return messages;
    }

}
