package com.codecool.chatter.controller;

import javafx.scene.Scene;

import java.io.IOException;
import java.net.Socket;

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
        try (Socket socket = new Socket(host, port)) {
            System.out.println("Client is running!");
            appController.setConnection(socket);
            appController.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Scene getMainScene() {
        return new Scene(appController.getAppView());
    }
}
