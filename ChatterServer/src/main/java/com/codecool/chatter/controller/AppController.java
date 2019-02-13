package com.codecool.chatter.controller;

import com.codecool.chatter.model.Lobby;
import com.codecool.chatter.model.ObjectWrapper;
import com.codecool.chatter.model.Room;
import com.codecool.chatter.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AppController {

    private Lobby lobby;

    private List<User> allUsers = new ArrayList<>();

    public AppController() {
        this.lobby = new Lobby();
        addUserAndRoomForTest();
    }

    public Lobby getLobby() {
        return lobby;
    }

    private void addUser(User user) {
        allUsers.add(user);
    }

    public Object wrapObject(String action, Object object) {
        return new ObjectWrapper(action, object);
    }

    private void addUserAndRoomForTest() {
        User user = new User("Stefan");
        User user2 = new User("Grażyna");
        Room room = new Room("towarzyski");
        allUsers.add(user);
        allUsers.add(user2);
        room.addUser(user2);
        lobby.addRoom(room);
        lobby.addUser(user);
    }

    public Object handleData(Object object, User user) {
        ObjectWrapper data = (ObjectWrapper) object;
        Object answer = null;
        String action = data.getAction();
        Object receiveData = data.getObject();
        switch (action) {
            case "chosenRoomId":
                answer = chooseRoomHandle(receiveData, user);
                break;
            case "createRoom":
//                String roomName = (String) receiveData;
//                if(checkRoomByNameExist(roomName)) {
//                    action = "isAvailable";
//                    answer = false;
//                }else{
//                    Room room = new Room(roomName);
//                    room.getUsers().add(user);
//                    answer = room;
//                }
                answer = handleCreateRoom(receiveData, user);
                break;
        }
        return wrapObject(action, answer);
    }

    public boolean checkNickNameExist(String userName) {
        return allUsers.stream().anyMatch(user -> userName.equals(user.getNickname()));
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

    private Room getRoomById(UUID id) {
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
