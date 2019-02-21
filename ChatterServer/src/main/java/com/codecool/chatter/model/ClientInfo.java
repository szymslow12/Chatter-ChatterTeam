package com.codecool.chatter.model;

public class ClientInfo {

    private Connection connection;
    private User user;
    private int latestMsgIndex;

    public ClientInfo(Connection connection, User user) {
        this.connection = connection;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public int getLatestMsgIndex() {
        return latestMsgIndex;
    }

    public void setLatestMsgIndex(int latestMsgIndex) {
        this.latestMsgIndex = latestMsgIndex;
    }

    public Connection getConnection() {
        return connection;
    }


}
