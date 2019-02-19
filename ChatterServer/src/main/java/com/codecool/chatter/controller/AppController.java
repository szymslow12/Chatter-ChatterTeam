package com.codecool.chatter.controller;

import com.codecool.chatter.model.*;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AppController {

    private Lobby lobby;

    private List<User> allUsers = new ArrayList<>();
    private List<ClientInfo> clients = new ArrayList<>();
    private static int msgId = 1;

    public AppController() {
        this.lobby = new Lobby();
        addUserAndRoomForTest();
        new DataStreamController(this).start();
    }

    public void addClient(Connection out, User user) {
//        allUsers.add(user);
        clients.add(new ClientInfo(out, user));
    }

    public void removeClient(User user) {
        for (ClientInfo client: clients) {
            if(client.getUser().equals(user)) clients.remove(client);
        }
    }

    public List<ClientInfo> getClientInfo() {
        return clients;
    }

    public synchronized Lobby getLobby() {
        return lobby;
    }

    private void addUser(User user) {
        allUsers.add(user);
    }

    public ObjectWrapper wrapObject(String action, Object object) {
        return new ObjectWrapper(action, object);
    }

    private void addUserAndRoomForTest() {
//        User user = new User("Stefan");
//        User user2 = new User("Grażyna");
        Room room = new Room("towarzyski");
        Chat chat = new Chat();
        room.setChat(chat);
//        allUsers.add(user);
//        allUsers.add(user2);
//        room.addUser(user2);
        lobby.addRoom(room);
//        lobby.addUser(user);
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
        }
        return new ObjectWrapper(action, answer);
    }

    private Object handleMessage(Object receiveData, User user) {
        Message msg = (Message) receiveData;
        System.out.println(msg.getContent());
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
        room.getUsers().add(user);
        lobby.getRooms().add(room);
        return room;
    }

    private boolean checkRoomByNameExist(String roomName) {
        return lobby.getRooms().stream().anyMatch(room -> room.getName().equalsIgnoreCase(roomName));
    }

    private Room getRoomByName(String roomName) {
        return lobby.getRooms().stream().filter(room -> room.getName().equalsIgnoreCase(roomName)).findFirst().get();
    }


}
