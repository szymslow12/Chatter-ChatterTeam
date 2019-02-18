package com.codecool.chatter.controller;

import com.codecool.chatter.ChatterClient;
import com.codecool.chatter.controller.eventHandler.CreateRoom;
import com.codecool.chatter.controller.eventHandler.EnterRoom;
import com.codecool.chatter.model.*;
import com.codecool.chatter.view.*;

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
        lobbyView = new LobbyView(ChatterClient.WIDTH, ChatterClient.HEIGHT);
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
            getLobbyFromServer();
            lobbyView.renderLobbyView(lobby, client, new EnterRoom(this), new CreateRoom(this));
            appView.getChildren().add(lobbyView);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void getLobbyFromServer() throws IOException, ClassNotFoundException {
        lobby = (Lobby) connection.read().getObject();
        updater.setReceived(true);
        System.out.println("Lobby has been loaded!");
    }


    public Updater getUpdater() {
        return updater;
    }
}
