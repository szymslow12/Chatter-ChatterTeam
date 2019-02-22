package com.codecool.chatter.controller;

import com.codecool.chatter.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataStreamController extends Thread {

    private AppController appController;

    public DataStreamController(AppController appController) {
        this.appController = appController;
    }

    public void run() {

        List<ClientInfo> clients;
        try {
            while (true) {
                clients = appController.getClientInfo();
                sendingUpdateDataToClients(clients);
                Thread.sleep(50);
            }
        }catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void sendingUpdateDataToClients(List<ClientInfo> clients) throws IOException {
        Connection connection;
        User user;
        for (int i = 0; i < clients.size(); i++) {
            ClientInfo client = clients.get(i);
            connection = client.getConnection();
            user = client.getUser();
            if (user.getCurrentRoomId() == null) {
                lobbyUpdate(connection, client);
            }
            else if (user.getCurrentRoomId() != null) {
                roomUpdate(connection, client);
            }
        }
    }

    private void roomUpdate(Connection connection, ClientInfo client) throws IOException {
        Room room = appController.getRoomById(client.getUser().getCurrentRoomId());
        List<Message> messageToSend = getMessageToSend(client, room);
        Room copyRoom = new Room(room.getName());
        copyRoom.setUsers(room.getUsers());

        copyRoom.getChat().setMessages(messageToSend);

        connection.write(appController.wrapObject("updateRoom", copyRoom));
    }

    private void lobbyUpdate(Connection connection, ClientInfo client) throws IOException {
        connection.write(appController.wrapObject("lobby", appController.getLobby()));
    }

    private List<Message> getMessageToSend(ClientInfo client, Room room) {
        int latestMsgIndex = client.getLatestMsgIndex();
        List<Message> messages = room.getChat().getMessages();
        List<Message> latestMsg = new ArrayList<>();
        int i;
        for(i = latestMsgIndex; i < messages.size(); i++) {
            if(!messages.get(i).getAuthor().getNickname().equals(client.getUser().getNickname()))
                latestMsg.add(messages.get(i));
        }
        client.setLatestMsgIndex(i);
        return latestMsg;
    }

}
