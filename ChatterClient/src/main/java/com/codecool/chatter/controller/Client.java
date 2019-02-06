package com.codecool.chatter.controller;

import javafx.scene.Scene;

import java.io.IOException;

public class Client {

    private AppController appController;

    public Client(String host, int port) {
        this.appController = new AppController(host, port);
    }

    //TODO
    public void run() {
        try {
            appController.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Scene getMainScene() {
        return new Scene(appController.getAppView());
    }
}
