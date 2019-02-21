package com.codecool.chatter.controller;

import javafx.scene.Scene;

public class Client {


    private AppController appController;
    private boolean isRunning;


    public Client(String host, int port, double width, double height) {
        this.appController = new AppController(host, port, width, height);
    }


    public void run() {
        appController.start();
    }


    public Scene getMainScene() {
        return new Scene(appController.getAppView());
    }


    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }


    public boolean isRunning() {
        return isRunning;
    }
}
