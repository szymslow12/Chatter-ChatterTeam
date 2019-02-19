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

        try {

            while (true) {
                clients = appController.getClientInfo();
                Connection connection;
                System.out.println(clients.size() + " clients count");
                for (ClientInfo client : clients) {

                    connection = client.getConnection();
                    user = client.getUser();

                    if (user.getCurrentRoomId() == null) {
                        sendLobby(connection, client);
                        break;

                    } else if (user.getCurrentRoomId() != null) {
                        roomUpdate(connection, client);
//                        Room room = sendRoomUsers(connection, client);
//                        sendNewMessagess(connection, client, room);
                    }
                }

                Thread.sleep(1000);
            }
        }catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void roomUpdate(Connection connection, ClientInfo client) throws IOException {
        Room room = appController.getRoomById(client.getUser().getCurrentRoomId());
        List<Message> messageToSend = getMessageToSend(client, room);

        Room copyRoom = new Room(room.getName());
        copyRoom.setUsers(room.getUsers());

        copyRoom.getChat().setMessages(messageToSend);

        copyRoom.getChat().setMessages(messageToSend);

        connection.write(appController.wrapObject("updateRoom", copyRoom));
    }

    private void sendNewMessagess(Connection connection, ClientInfo client, Room room) throws IOException {
        List<Message> messages = room.getChat().getMessages();
        List<Message> messageToSend = getMessageToSend(client, room);
        ObjectWrapper newMessages = appController.wrapObject("updateChat", messageToSend);
        connection.write(newMessages);
    }

    private Room sendRoomUsers(Connection connection, ClientInfo client) throws IOException {
        Room room = appController.getRoomById(client.getUser().getCurrentRoomId());

        ObjectWrapper roomUsers = appController.wrapObject("roomUsers", room.getUsers());
        connection.write(roomUsers);
        return room;
    }

    private void sendLobby(Connection connection, ClientInfo client) throws IOException {
        connection.write(appController.wrapObject("lobby", appController.getLobby()));
    }

    private List<Message> getMessageToSend(ClientInfo client, Room room) {
        int latestMsgIndex = client.getLatestMsgIndex();
        List<Message> messages = room.getChat().getMessages();
        List<Message> latestMsg = new ArrayList<>();
        int i;
        for(i = latestMsgIndex; i < messages.size(); i++) {
            if(messages.get(i).getAuthor().equals(client.getUser().getNickname()))
                latestMsg.add(messages.get(i));
        }
        client.setLatestMsgIndex(i);
        return latestMsg;
    }

}
