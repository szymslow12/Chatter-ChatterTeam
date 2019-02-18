package com.codecool.chatter.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Room implements Serializable {

    private UUID id = UUID.randomUUID();
    private String name;
    private int maxCapacity;
    private List<User> users;
    private Chat chat;


    public Room(String name) {
        this.name = name;
        this.maxCapacity = 20;
        this.users = new ArrayList<>();
    }


    public void addUser(User user) {
        user.setCurrentRoomId(id);
        users.add(user);
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Chat getChat() {
        return chat;
    }


    public void setChat(Chat chat) {
        this.chat = chat;
    }


    public int getMaxCapacity() {
        return maxCapacity;
    }


    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public List<User> getUsers() {
        return users;
    }


    public void setUsers(List<User> users) {
        this.users = users;
    }
}
