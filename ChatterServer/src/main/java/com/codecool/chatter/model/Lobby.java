package com.codecool.chatter.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Lobby implements Serializable {

    private List<Room> rooms;
    private transient List<User> users;


    public Lobby() {
        this.rooms = new ArrayList<>();
        this.users = new ArrayList<>();
    }


    public void addRoom(Room room) {
        rooms.add(room);
    }


    public void addUser(User user) {
        users.add(user);
    }


    public void setUsers(List<User> users) {
        this.users = users;
    }


    public List<User> getUsers() {
        return users;
    }


    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }


    public List<Room> getRooms() {
        return rooms;
    }

    public boolean checkNickNameExist(String userName) {
        return users.stream().anyMatch(user -> userName.equalsIgnoreCase(user.getNickname()));
    }
}
