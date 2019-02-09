package com.codecool.chatter.controller;

import com.codecool.chatter.ObjectWrapper;
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

    public Object wrapObject(String action, Object object) {
        return new ObjectWrapper(action, object);
    }

    public void handleData(Object object) {
        ObjectWrapper data = (ObjectWrapper) object;
        String action = data.getAction();
        switch (action) {
            case "login":
                break;
            case "":
        }

    }



}
