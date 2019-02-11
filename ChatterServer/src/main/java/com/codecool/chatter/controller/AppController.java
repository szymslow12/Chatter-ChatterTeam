package com.codecool.chatter.controller;

import com.codecool.chatter.model.Lobby;
import com.codecool.chatter.model.ObjectWrapper;
import com.codecool.chatter.model.Room;
import com.codecool.chatter.model.User;

import java.util.ArrayList;
import java.util.List;

public class AppController {

    private Lobby lobby;

    private List<User> allUsers = new ArrayList<>();

    public AppController() {
        this.lobby = new Lobby();
    }

    public Lobby getLobby() {
        return lobby;
    }

    public Object wrapObject(String action, Object object) {
        return new ObjectWrapper(action, object);
    }

    public Object handleData(Object object, User user) {
        ObjectWrapper data = (ObjectWrapper) object;
        Object answer = null;
        String action = data.getAction();
        Object receiveData = data.getObject();
        switch (action) {
            case "chosenRoomId":
                answer = chooseRoomHandle(receiveData , user);
                break;
            case "createRoom":
                answer = handleCreateRoom(receiveData, user);
                break;
        }
        return answer;
    }

    public boolean checkNickNameExist(String userName) {
        return allUsers.stream().anyMatch(user -> userName.equalsIgnoreCase(user.getNickname()));
    }

    private Object chooseRoomHandle(Object object, User user) {
        long id = (long) object;
        if(checkRoomByIdExist(id)) {
            Room room = getRoomById(id);
            room.addUser(user);
            return room;
        }
        return IllegalArgumentException.class;
    }

    private boolean checkRoomByIdExist(long id) {
        return lobby.getRooms().stream().anyMatch(room -> id == room.getId());
    }

    private Room getRoomById(long id) {
        return lobby.getRooms().stream().filter(room -> room.getId() == id).findFirst().get();
    }

    private Object handleCreateRoom(Object receiveData, User user) {
        String roomName = (String) receiveData;
        if(checkRoomByNameExist(roomName)) {
            return false;
        }
        Room room = new Room(roomName, 8080);
        room.getUsers().add(user);
        return room;
    }

    private boolean checkRoomByNameExist(String roomName) {
        return lobby.getRooms().stream().anyMatch(room -> room.getName().equalsIgnoreCase(roomName));
    }

    private Room getRoomByName(String roomName) {
        return lobby.getRooms().stream().filter(room -> room.getName().equalsIgnoreCase(roomName)).findFirst().get();
    }


}
