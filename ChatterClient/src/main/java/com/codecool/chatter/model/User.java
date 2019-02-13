package com.codecool.chatter.model;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {

    private String nickname;
    private UUID currentRoomId;

    public User(String nickname) {
        this.nickname = nickname;
        this.currentRoomId = null;
    }


    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getNickname() {
        return nickname;
    }


    public UUID getCurrentRoomId() {
        return currentRoomId;
    }


    public void setCurrentRoomId(UUID currentRoomId) {
        this.currentRoomId = currentRoomId;
    }
}
