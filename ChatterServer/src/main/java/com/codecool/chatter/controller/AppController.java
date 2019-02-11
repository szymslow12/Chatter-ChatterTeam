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
        switch (action) {
            case "chosenRoomId":
                answer = chooseRoomHandle((long) data.getObject(), user);
                break;
        }
        return answer;
    }

    public boolean checkNickNameExist(String userName) {
        return allUsers.stream().anyMatch(user -> userName.equalsIgnoreCase(user.getNickname()));
    }

    private Object chooseRoomHandle(long id, User user) {
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
}
