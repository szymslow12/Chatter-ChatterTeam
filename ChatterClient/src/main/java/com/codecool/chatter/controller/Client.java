package com.codecool.chatter.controller;

import com.codecool.chatter.model.Connection;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.Socket;

public class Client extends Thread {

    private String host;
    private int port;
    private AppController appController;
    private boolean isRunning;


    public Client(String host, int port, double width, double height) {
        this.host = host;
        this.port = port;
        this.appController = new AppController(width, height);
        this.isRunning = true;
    }


    @Override
    public void run() {
        try {
            Socket socket = new Socket(host, port);
            Connection connection = new Connection(socket.getOutputStream(), socket.getInputStream());

        } catch (IOException err) {
            err.printStackTrace();
        }
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
