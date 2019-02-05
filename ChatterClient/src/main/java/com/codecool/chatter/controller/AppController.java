package com.codecool.chatter.controller;

import com.codecool.chatter.model.User;

import java.net.Socket;

public class AppController {

    private Socket connection;
//    private LobbyController lobbyController;
//    private RoomController roomController;
//    private AppView appView;
    private User client;

    public AppController(){}


    public AppController(Socket connection) {
        this.connection = connection;
//        this.lobbyController = new LobbyController(connection);
//        this.roomController = new RoomController(connection);
    }


    //TODO
    public void run() {

    }


    public void setConnection(Socket connection) {
        this.connection = connection;
    }
}