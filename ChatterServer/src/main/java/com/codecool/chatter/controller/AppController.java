package com.codecool.chatter.controller;

import com.codecool.chatter.model.ObjectWrapper;
import com.codecool.chatter.model.Lobby;

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

    public Object handleData(Object object) {
        ObjectWrapper data = (ObjectWrapper) object;
        Object answer = null;
        String action = data.getAction();
        switch (action) {
            case "login":
                answer = checkNickNameExist((String) data.getObject());
                break;
            case "lobby":
                answer = lobby;
                break;
        }
        return answer;
    }

    public boolean checkNickNameExist(String userName) {
        return lobby.getUsers().stream().anyMatch(user -> userName.equalsIgnoreCase(user.getNickname()));
    }
}
