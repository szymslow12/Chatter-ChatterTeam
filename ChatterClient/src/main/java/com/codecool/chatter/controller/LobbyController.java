package com.codecool.chatter.controller;

import com.codecool.chatter.ChatterClient;
import com.codecool.chatter.model.Lobby;
import com.codecool.chatter.view.LobbyView;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class LobbyController {

    private Socket connection;
    private Lobby lobby;
    private LobbyView lobbyView;
    private Long chosenRoomId;

    public LobbyController(Socket connection) {
        this.connection = connection;
        lobby = null;
        lobbyView = new LobbyView(ChatterClient.WIDTH, ChatterClient.HEIGHT);
        chosenRoomId = null;
    }


    public void run() throws IOException, ClassNotFoundException {
        while (chosenRoomId == null) {
            getLobbyFromServer();
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
