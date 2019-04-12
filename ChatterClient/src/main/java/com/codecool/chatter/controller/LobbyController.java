package com.codecool.chatter.controller;

import com.codecool.chatter.controller.eventHandler.CreateRoom;
import com.codecool.chatter.controller.eventHandler.EnterRoom;
import com.codecool.chatter.model.*;
import com.codecool.chatter.view.*;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;

import java.io.IOException;

public class LobbyController extends Controller<Room>{

    private Lobby lobby;

    public LobbyController(Connection connection, Updater updater) {
        super(null, new LobbyView(Client.WIDTH, Client.HEIGHT), connection, updater);
        lobby = null;
    }


    public void run(AppView appView, User client) {
        try {
            EventHandler<InputEvent> enterRoom = new EnterRoom(this);
            EventHandler<InputEvent> createRoom = new CreateRoom(this);
            LobbyView lobbyView = (LobbyView) getUpdatable();
            getLobbyFromServer();
            lobbyView.renderLobbyView(lobby, client, enterRoom, createRoom);
            appView.getChildren().add(lobbyView);
            startLobbyUpdater(enterRoom, lobbyView);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void getLobbyFromServer() throws IOException, ClassNotFoundException {
        lobby = (Lobby) getConnection().read().getObject();
        System.out.println("Lobby has been loaded!");
    }


    private void startLobbyUpdater(EventHandler<InputEvent> eventHandler, LobbyView lobbyView) {
        Updater updater = getUpdater();
        updater.setEventHandler(eventHandler);
        updater.setUpdatable(lobbyView);
        updater.start();
    }
}
