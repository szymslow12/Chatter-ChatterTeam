package com.codecool.chatter.controller;

import com.codecool.chatter.model.Room;
import com.codecool.chatter.model.User;
import com.codecool.chatter.view.AppView;
import javafx.application.Platform;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.flush();
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            runLoginController(objectOutputStream, objectInputStream);
            runLobbyController(objectOutputStream, objectInputStream);
            runRoomController(objectOutputStream, objectInputStream);
            interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void runLoginController(ObjectOutputStream outputStream, ObjectInputStream inputStream) {
        LoginController loginController = new LoginController(outputStream, inputStream);
        Platform.runLater(loginController::runLoginStage);
        while (client == null) {
            client = loginController.getClient();
        }
        System.out.println("Client nickname=" + client.getNickname() + " has logged in...");
    }


    private void runLobbyController(ObjectOutputStream outputStream, ObjectInputStream inputStream) {
        LobbyController lobbyController = new LobbyController(outputStream, inputStream);
        Platform.runLater(() -> lobbyController.run(appView, client));
        Room chosenRoom = null;
        while (chosenRoom == null) {
            chosenRoom = lobbyController.getChosenRoom();
        }
        System.out.println("Entering room " + chosenRoom.getName() + "...");
        client.setCurrentRoomId(chosenRoom.getId());
    }


    private void runRoomController(ObjectOutputStream outputStream, ObjectInputStream inputStream) {
        RoomController roomController = new RoomController(outputStream, inputStream);
        roomController.run();
    }


    public AppView getAppView() {
        return appView;
    }
}