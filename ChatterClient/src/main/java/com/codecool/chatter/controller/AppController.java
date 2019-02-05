package com.codecool.chatter.controller;

import com.codecool.chatter.model.User;
import com.codecool.chatter.view.AppView;

import java.net.Socket;

public class AppController {

    private Socket connection;
//    private LobbyController lobbyController;
//    private RoomController roomController;
    private AppView appView;
    private User client;

    public AppController(){
        this.appView = new AppView();
    }


    public AppController(Socket connection) {
        this();
        this.connection = connection;
//        this.lobbyController = new LobbyController(connection);
//        this.roomController = new RoomController(connection);
    }


    //TODO
    public void run() {
        LoginController loginController = new LoginController();
        loginController.run();
    }


    public void setConnection(Socket connection) {
        this.connection = connection;
    }


    public AppView getAppView() {
        return appView;
    }
}