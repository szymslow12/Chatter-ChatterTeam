package com.codecool.chatter.controller;

import javafx.scene.Scene;

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
        System.out.println("Client is running!");
        appController.run();
    }


    public Scene getMainScene() {
        return new Scene(appController.getAppView());
    }
}
