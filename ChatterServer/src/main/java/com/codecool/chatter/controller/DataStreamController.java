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
        User user;
        for (int i = 0; i < clients.size(); i++) {
            ClientInfo client = clients.get(i);
            user = client.getUser();
            if (user.getCurrentRoomId() == null && isLobbyChange(client)) {
                System.out.println("sending looby to: " + client.getUser().getNickname());
                lobbyUpdate(client);
            }
            else if (user.getCurrentRoomId() != null && isRoomChange(client)) {
                System.out.println("sending room to: " + client.getUser().getNickname());
                roomUpdate(client);
            }
        }
    }

    private boolean isRoomChange(ClientInfo client) {
        Room room = appController.getRoomById(client.getUser().getCurrentRoomId());
//        System.out.println("last number of users in room: " + client.getLastNumberOfUsersInRoom());
//        System.out.println("numbers of users in room: " + room.getUsers().size());
        boolean isDifferentUsersCount = client.getLastNumberOfUsersInRoom() != room.getUsers().size();

        long lastMsgID = 1;
        if(room.getChat().getMessages().size() > 0){
            int lastIndex = room.getChat().getMessages().size() - 1;
            lastMsgID = room.getChat().getMessages().get(lastIndex).getId();
        }
//        System.out.println(lastMsgID + " last msgId");
//        System.out.println(client.getLatestMsgIndex() + " last clinet id msg");
        boolean isDifferentLastMsg = lastMsgID != client.getLatestMsgIndex();

        return isDifferentUsersCount || isDifferentLastMsg;
    }

    private boolean isLobbyChange(ClientInfo client) {
        boolean isDifferentRoomsSize = client.getLastNumberOfRoomsInLobby() != appController.getLobby().getRooms().size();
        boolean isDifferentUsersSize = client.getLastNumberOfUsersInLobby() != appController.getLobby().getUsers().size();
        return isDifferentRoomsSize || isDifferentUsersSize;
    }

    private void roomUpdate(ClientInfo client) throws IOException {
        Room room = appController.getRoomById(client.getUser().getCurrentRoomId());
        List<Message> messageToSend = getMessageToSend(client, room);
//        Room copyRoom = new Room(room.getName());
//        copyRoom.setUsers(room.getUsers());
//        copyRoom.getChat().setMessages(messageToSend);
        room.getChat().setMessages(messageToSend);
        client.setLastNumberOfUsersInRoom(room.getUsers().size());
        client.getConnection().write(appController.wrapObject("updateRoom", room));
    }

    private void lobbyUpdate(ClientInfo client) throws IOException {
        client.setLastNumberOfRoomsInLobby(appController.getLobby().getRooms().size());
        client.setLastNumberOfUsersInLobby(appController.getLobby().getUsers().size());
        client.getConnection().write(appController.wrapObject("lobby", appController.getLobby()));
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
