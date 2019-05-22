package com.codecool.chatter.controller;

import com.codecool.chatter.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AppController {

    private Lobby lobby;

    private List<ClientInfo> clients = new ArrayList<>();
    private static int msgId = 1;

    public AppController() {
        this.lobby = new Lobby();
        addUserAndRoomForTest();
        new DataStreamController(this).start();
    }

    public void addClient(Connection out, User user) {
        ClientInfo client = new ClientInfo(out, user);
        client.setLastNumberOfUsersInLobby(lobby.getUsers().size());
        client.setLastNumberOfRoomsInLobby(lobby.getRooms().size());
        clients.add(client);
    }

    public void removeClient(User user) {
        for (int i = 0; i < clients.size(); i++) {
            ClientInfo client = clients.get(i);
            if (client.getUser().equals(user)) {
                System.out.println("remove user: " + client.getUser().getNickname());
                clients.remove(client);
            }
        }
    }

    public List<ClientInfo> getClientInfo() {
        return clients;
    }

    public synchronized Lobby getLobby() {
        return lobby;
    }

    public ObjectWrapper wrapObject(String action, Object object) {
        if (object == null) {
            return new ObjectWrapper<Object>(action, null);
        } else if (object instanceof Lobby) {
            return new ObjectWrapper<>(action, (Lobby) object);
        } else if (object instanceof Room) {
            return new ObjectWrapper<>(action, (Room) object);
        } else if (object instanceof Boolean) {
            return new ObjectWrapper<>(action, (Boolean) object);
        } else if (object instanceof Message) {
            return new ObjectWrapper<>(action, (Message) object);
        } else {
            return null;
        }

    }

    private void addUserAndRoomForTest() {
        Room room = new Room("towarzyski");
        Chat chat = new Chat();
        room.setChat(chat);
        lobby.addRoom(room);
    }

    public ObjectWrapper handleData(ObjectWrapper data, User user) {
        Object answer = null;
        String action = data.getAction();
        Object receiveData = data.getObject();
        switch (action) {
            case "chosenRoomId":
                answer = chooseRoomHandle(receiveData, user);
                break;
            case "createRoom":
                answer = handleCreateRoom(receiveData, user);
                break;
            case "message":
                answer = handleMessage(receiveData, user);
                break;
            case "exitRoom":
                answer = handleRoomExit(user);
        }
        return wrapObject(action, answer);
    }

    private Object handleRoomExit(User user) {
        Room room = getRoomById(user.getCurrentRoomId());
        user.setCurrentRoomId(null);
        room.getUsers().remove(user);

        return lobby;
    }

    private Object handleMessage(Object receiveData, User user) {
        Message msg = (Message) receiveData;
        UUID roomId = user.getCurrentRoomId();
        msg.setId(msgId++);
        getRoomById(roomId).getChat().addMessage(msg);
        return true;
    }

    public boolean checkNickNameExist(String userName) {
        return clients.stream().anyMatch(clientInfo -> clientInfo.getUser().getNickname().equals(userName));
    }

    private Object chooseRoomHandle(Object object, User user) {
        UUID id = (UUID) object;
        if (checkRoomByIdExist(id)) {
            Room room = getRoomById(id);
            room.addUser(user);
            ClientInfo client = getClientByUser(user);
            client.setLatestMsgIndex(msgId);
            client.setLastNumberOfUsersInRoom(room.getUsers().size());
            return room;
        }
        return IllegalArgumentException.class;
    }

    private boolean checkRoomByIdExist(UUID id) {
        return lobby.getRooms().stream().anyMatch(room -> id.equals(room.getId()));
    }

    public Room getRoomById(UUID id) {
        return lobby.getRooms().stream().filter(room -> room.getId().equals(id)).findFirst().get();
    }

    private Object handleCreateRoom(Object receiveData, User user) {
        String roomName = (String) receiveData;
        if (checkRoomByNameExist(roomName)) {
            return null;
        }
        Room room = new Room(roomName);
        room.addUser(user);
        lobby.addRoom(room);
        return room;
    }

    private boolean checkRoomByNameExist(String roomName) {
        return lobby.getRooms().stream().anyMatch(room -> room.getName().equalsIgnoreCase(roomName));
    }

    private Room getRoomByName(String roomName) {
        return lobby.getRooms().stream().filter(room -> room.getName().equalsIgnoreCase(roomName)).findFirst().get();
    }

    private ClientInfo getClientByUser(User user) {
        return clients.stream().filter(clientInfo -> clientInfo.getUser().equals(user)).findFirst().get();
    }

}
