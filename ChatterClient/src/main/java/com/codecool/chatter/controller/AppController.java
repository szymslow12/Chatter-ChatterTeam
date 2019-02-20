package com.codecool.chatter.controller;

import com.codecool.chatter.controller.eventHandler.EnterRoom;
import com.codecool.chatter.model.Connection;
import com.codecool.chatter.model.Room;
import com.codecool.chatter.model.User;
import com.codecool.chatter.view.AppView;
import javafx.application.Platform;

import java.io.IOException;;
import java.net.Socket;

public class AppController extends Thread {

    private String host;
    private int port;
    private AppView appView;
    private User client;
    private Room chosenRoom;
    private Updater updater;

    public AppController(String host, int port, double width, double height) {
        this.host = host;
        this.port = port;
        this.appView = new AppView(width, height);
        client = null;
        chosenRoom = null;
        setName("AppController");
    }


    @Override
    public void run() {
        try {
            Socket socket = new Socket(host, port);
            Connection connection = new Connection(socket.getOutputStream(), socket.getInputStream());
            runLoginController(connection);
            runLobbyController(connection);
            runRoomController(connection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void runLoginController(Connection connection) {
        LoginController loginController = new LoginController(connection);
        Platform.runLater(loginController::runLoginStage);
        while (client == null) {
            client = loginController.getClient();
        }
        updater = new Updater(client, connection);
        System.out.println("Client nickname=" + client.getNickname() + " has logged in...");
    }


    private void runLobbyController(Connection connection) {
        LobbyController lobbyController = new LobbyController(connection, updater);
        Platform.runLater(() -> lobbyController.run(appView, client));
        while (chosenRoom == null) {
            chosenRoom = lobbyController.getChosenRoom();
        }
        updater = new Updater(chosenRoom, connection);
        System.out.println("Entering room " + chosenRoom.getName() + "...");
        client.setCurrentRoomId(chosenRoom.getId());
    }


    private void runRoomController(Connection connection) {
        RoomController roomController = new RoomController(connection, chosenRoom, updater);
        Platform.runLater(() ->
            {
                appView.getChildren().clear();
                roomController.run(appView, client);
            }
        );

    }


    public AppView getAppView() {
        return appView;
    }
}