package com.codecool.chatter.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Chat implements Serializable {

    private List<Message> messages;
    private transient User client;

    public Chat() {
        this.messages = new ArrayList<>();
    }


    public Chat(User client) {
        this();
        this.client = client;
    }


    public List<Message> getMessages() {
        return messages;
    }


    public void addMessage(Message message) {
        messages.add(message);
    }


    public User getClient() {
        return client;
    }


    public void setClient(User client) {
        this.client = client;
    }
}
