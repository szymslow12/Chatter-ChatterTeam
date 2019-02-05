package com.codecool.chatter.model;

public class User {

    private String nickname;
    private Long currentRoomId;

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


    public Long getCurrentRoomId() {
        return currentRoomId;
    }


    public void setCurrentRoomId(Long currentRoomId) {
        this.currentRoomId = currentRoomId;
    }
}
