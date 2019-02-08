package com.codecool.chatter.controller;

import com.codecool.chatter.model.Room;
import com.codecool.chatter.model.User;
import com.codecool.chatter.view.AppView;
import javafx.application.Platform;

import java.io.IOException;
import java.net.Socket;

public class AppController extends Thread {

    private String host;
    private int port;
    private AppView appView;
    private User client;

    public AppController(String host, int port, double width, double height) {
        this.host = host;
        this.port = port;
        this.appView = new AppView(width, height);
        client = null;
        setName("AppController");
    }


    @Override
    public void run() {
        try (Socket socket = new Socket(host, port)) {
            LoginController loginController = new LoginController(socket);
            Platform.runLater(loginController::runLoginStage);
            while (client == null) {
                client = loginController.getClient();
            }
            System.out.println("Client nickname=" + client.getNickname() + " has logged in...");
            LobbyController lobbyController = new LobbyController(socket);
            Platform.runLater(() -> lobbyController.run(appView));
            Room chosenRoom = null;
            while (chosenRoom == null) {
                chosenRoom = lobbyController.getChosenRoom();
            }
            System.out.println("Entering room " + chosenRoom.getName() + "...");
            chosenRoom.addUser(client);
            interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public AppView getAppView() {
        return appView;
    }
}