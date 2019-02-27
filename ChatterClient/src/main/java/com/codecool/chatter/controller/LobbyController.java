package com.codecool.chatter.controller;

import com.codecool.chatter.controller.eventHandler.CreateRoom;
import com.codecool.chatter.controller.eventHandler.EnterRoom;
import com.codecool.chatter.model.*;
import com.codecool.chatter.view.*;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;

import java.io.IOException;

public class LobbyController {

    private Connection connection;
    private Lobby lobby;
    private LobbyView lobbyView;
    private Updater updater;
    private volatile Room chosenRoom;


    public LobbyController(Connection connection, Updater updater) {
        this.connection = connection;
        this.updater = updater;
        lobby = null;
        chosenRoom = null;
        lobbyView = new LobbyView(Client.WIDTH, Client.HEIGHT);
    }


    public void setChosenRoom(Room room) {
        this.chosenRoom = room;
    }


    public Room getChosenRoom() {
        return chosenRoom;
    }


    public Connection getConnection() {
        return connection;
    }


    public LobbyView getLobbyView() {
        return lobbyView;
    }


    public void run(AppView appView, User client) {
        try {
            EventHandler<InputEvent> enterRoom = new EnterRoom(this);
            EventHandler<InputEvent> createRoom = new CreateRoom(this);
            getLobbyFromServer();
            lobbyView.renderLobbyView(lobby, client, enterRoom, createRoom);
            appView.getChildren().add(lobbyView);
            startLobbyUpdater(enterRoom);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void getLobbyFromServer() throws IOException, ClassNotFoundException {
        lobby = (Lobby) connection.read().getObject();
        System.out.println("Lobby has been loaded!");
    }


    private void startLobbyUpdater(EventHandler<InputEvent> eventHandler) {
        updater.setEventHandler(eventHandler);
        updater.setUpdatable(lobbyView);
        updater.start();
    }

    public Updater getUpdater() {
        return updater;
    }
}
