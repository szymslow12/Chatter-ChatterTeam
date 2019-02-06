package com.codecool.chatter.controller;

import com.codecool.chatter.model.User;
import com.codecool.chatter.view.AppView;
import javafx.application.Platform;

import java.io.IOException;
import java.net.Socket;

public class AppController {

    private String host;
    private int port;
//    private LobbyController lobbyController;
//    private RoomController roomController;
    private AppView appView;
    private User client;

    //changes to UML add field host/port
    public AppController(String host, int port) {
        this.host = host;
        this.port = port;
        this.appView = new AppView();
//        this.lobbyController = new LobbyController(connection);
//        this.roomController = new RoomController(connection);
    }


    //TODO
    public void run() throws IOException {
        Socket socket = new Socket(host, port);
        LoginController loginController = new LoginController(socket);
        loginController.run();
    }


    public AppView getAppView() {
        return appView;
    }
}