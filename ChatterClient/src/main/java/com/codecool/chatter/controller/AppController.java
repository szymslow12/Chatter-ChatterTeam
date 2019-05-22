package com.codecool.chatter.controller;

import com.codecool.chatter.controller.eventHandler.ExitProgram;
import com.codecool.chatter.model.Connection;
import com.codecool.chatter.model.Room;
import com.codecool.chatter.model.User;
import com.codecool.chatter.view.AppView;
import javafx.application.Platform;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class AppController extends Thread {

    private String host;
    private int port;
    private AppView appView;
    private volatile User client;
    private volatile Room chosenRoom;
    private volatile Updater updater;
    private volatile boolean isRunning;

    public AppController(String host, int port, double width, double height) {
        this.appView = new AppView(width, height);
        client = null;
        chosenRoom = null;
        this.isRunning = true;
        this.host = host;
        this.port = port;
    }


    @Override
    public void run() {
        try {
            Socket socket = new Socket(host, port);
            DatagramSocket datagramSocket = new DatagramSocket(8081);
            InetAddress address = InetAddress.getByName("localhost");
            Connection connection = new Connection(socket.getOutputStream(),
                    socket.getInputStream(),
                    datagramSocket,
                    address);

            runLoginController(connection);
            while (isRunning) {
                runLobbyController(connection);
                runRoomController(connection);
            }
            connection.close();
            socket.close();
            System.out.println("User exits program...");
        } catch (IOException err) {
            err.printStackTrace();
        }
    }


    public void runLoginController(Connection connection) {
        LoginController loginController = new LoginController(connection);
        Platform.runLater(loginController::runLoginStage);
        while (client == null) {
            client = loginController.getClient();
            if (!isRunning) {
                return;
            }
        }
        updater = new Updater(client, connection);
        System.out.println("Client nickname=" + client.getNickname() + " has logged in...");
    }


    public void runLobbyController(Connection connection) {
        LobbyController lobbyController = new LobbyController(connection, updater);
        Platform.runLater(() -> {
            appView.getChildren().clear();
            lobbyController.run(appView, client);
        });
        while (chosenRoom == null) {
            chosenRoom = lobbyController.getControlType();
            if (!isRunning) {
                chosenRoom = new Room(null);
                return;
            }
        }
        updater = new Updater(chosenRoom, connection);
        System.out.println("Entering room " + chosenRoom.getName() + "...");
        client.setCurrentRoomId(chosenRoom.getId());
    }


    public void runRoomController(Connection connection) {
        RoomController roomController = new RoomController(connection, chosenRoom, updater);
        Platform.runLater(() -> {
            appView.getChildren().clear();
            roomController.run(appView, client);
        });
        while (chosenRoom != null) {
            chosenRoom = roomController.getControlType();
            if (!isRunning) {
                return;
            }
        }
        updater = new Updater(client, connection);
        client.setCurrentRoomId(null);
        System.out.println("Entering Lobby...");
    }


    public AppView getAppView() {
        return appView;
    }


    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }


    public Updater getUpdater() {
        return updater;
    }


    public void setChosenRoom(Room room) {
        this.chosenRoom = room;
    }


    public void setClient(User client) {
        this.client = client;
    }


    public ExitProgram getExitProgram() {
        return new ExitProgram(this);
    }
}