package com.codecool.chatter.controller;

import java.net.Socket;

public class Client {

    private Socket socket;
    private String host;
    private int port;
//    private AppController appController;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
//        this.appController = new AppController();
    }

    //TODO
    public void run() {

    }
}
