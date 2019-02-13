package com.codecool.chatter.model;

import java.io.ObjectOutputStream;

public class ClientInfo {

    private ObjectOutputStream outputStream;
    private User user;

    public ClientInfo(ObjectOutputStream outputStream, User user) {
        this.outputStream = outputStream;
        this.user = user;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public User getUser() {
        return user;
    }
}
