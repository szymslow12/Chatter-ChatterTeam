package com.codecool.chatter.controller;

import com.codecool.chatter.ChatterClient;
import com.codecool.chatter.model.Lobby;
import com.codecool.chatter.view.AppView;
import com.codecool.chatter.view.ButtonView;
import com.codecool.chatter.view.LobbyView;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class LobbyController {

    private Socket connection;
    private Lobby lobby;
    private LobbyView lobbyView;
    private Long chosenRoomId;

    private EventHandler<MouseEvent> enterRoom = e -> {
        ButtonView buttonView = (ButtonView) e.getSource();
        chosenRoomId = buttonView.getRoomId();
        System.out.println(chosenRoomId);
    };

    public LobbyController(Socket connection) {
        this.connection = connection;
        lobby = null;
        chosenRoomId = null;
    }


    public void run(AppView appView) throws IOException, ClassNotFoundException {
        lobbyView = new LobbyView(ChatterClient.WIDTH, ChatterClient.HEIGHT);
        getLobbyFromServer();
        lobbyView.renderLobbyView(lobby, enterRoom);
        Platform.runLater(() -> appView.getChildren().add(lobbyView));
        while (chosenRoomId == null) {

        }
        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.writeLong(chosenRoomId);

    }


    private void getLobbyFromServer() throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(connection.getInputStream());
        lobby = (Lobby) inputStream.readObject();
        System.out.println("Lobby has been loaded! " + lobby);
    }
}
