package com.codecool.chatter.controller;

import javafx.scene.Scene;

public class Client {

    private AppController appController;
    //changes to UML removed field host/port

    public Client(String host, int port) {
        this.appController = new AppController(host, port);
    }

    //TODO
    public void run() {
        appController.start();
    }


    public Scene getMainScene() {
        return new Scene(appController.getAppView());
    }
}
