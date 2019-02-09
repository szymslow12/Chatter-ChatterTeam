package com.codecool.chatter.controller;

import com.codecool.chatter.model.Lobby;

import java.io.IOException;
import java.io.ObjectInputStream;

public class AppController {

    private Lobby lobby;

    public AppController() {
        this.lobby = new Lobby();
    }

    public Lobby getLobby() {
        return lobby;
    }

    public boolean checkNickNameExist(ObjectInputStream objectInputStream) throws IOException {
        String userName = objectInputStream.readUTF();
        return lobby.getUsers().stream().anyMatch(user -> userName.equalsIgnoreCase(user.getNickname()));
    }


}
