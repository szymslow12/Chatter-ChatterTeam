package com.codecool.chatter.model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientInfo {

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private User user;
    private int latestMsgIndex;

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

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public int getLatestMsgIndex() {
        return latestMsgIndex;
    }

    public void setLatestMsgIndex(int latestMsgIndex) {
        this.latestMsgIndex = latestMsgIndex;
    }
}
