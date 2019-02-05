package com.codecool.chatter.controller;

public class Client {

    private String host;
    private int port;
    private AppController appController;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
        this.appController = new AppController();
    }

    //TODO
    public void run() {

    }
}
