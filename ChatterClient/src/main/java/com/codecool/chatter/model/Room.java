package com.codecool.chatter.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Room implements Serializable {

    private long id;
    private String name;
    private int maxCapacity;
    private List<User> users;
    private int port;
    private Chat chat;


    public Room(long id, String name, int port) {
        this.id = id;
        this.name = name;
        this.port = port;
        this.maxCapacity = 20;
        this.users = new ArrayList<>();
    }


    public void addUser(User user) {
        users.add(user);
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
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


    public int getPort() {
        return port;
    }


    public void setPort(int port) {
        this.port = port;
    }


    public List<User> getUsers() {
        return users;
    }


    public void setUsers(List<User> users) {
        this.users = users;
    }
}
