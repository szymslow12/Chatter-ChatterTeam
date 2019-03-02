package com.codecool.chatter.model;

public class ClientInfo {

    private Connection connection;
    private User user;
    private boolean allowRoomUpdate = false;
    private boolean allowLobbyUpdate = false;
    private int latestMsgIndex;
    private int lastNumberOfUsersInLobby;
    private int lastNumberOfRoomsInLobby;
    private int lastNumberOfUsersInRoom = 1;

    public ClientInfo(Connection connection, User user) {
        this.connection = connection;
        this.user = user;
    }

    public boolean isAllowRoomUpdate() {
        return allowRoomUpdate;
    }

    public void setAllowRoomUpdate(boolean allowRoomUpdate) {
        this.allowRoomUpdate = allowRoomUpdate;
    }

    public boolean isAllowLobbyUpdate() {
        return allowLobbyUpdate;
    }

    public void setAllowLobbyUpdate(boolean allowLobbyUpdate) {
        this.allowLobbyUpdate = allowLobbyUpdate;
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

    public int getLastNumberOfUsersInLobby() {
        return lastNumberOfUsersInLobby;
    }

    public void setLastNumberOfUsersInLobby(int lastNumberOfUsersInLobby) {
        this.lastNumberOfUsersInLobby = lastNumberOfUsersInLobby;
    }

    public int getLastNumberOfRoomsInLobby() {
        return lastNumberOfRoomsInLobby;
    }

    public void setLastNumberOfRoomsInLobby(int lastNumberOfRoomsInLobby) {
        this.lastNumberOfRoomsInLobby = lastNumberOfRoomsInLobby;
    }

    public int getLastNumberOfUsersInRoom() {
        return lastNumberOfUsersInRoom;
    }

    public void setLastNumberOfUsersInRoom(int lastNumberOfUsersInRoom) {
        this.lastNumberOfUsersInRoom = lastNumberOfUsersInRoom;
    }
}
