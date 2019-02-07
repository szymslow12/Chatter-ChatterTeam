package com.codecool.chatter.controller;

import com.codecool.chatter.ChatterClient;
import com.codecool.chatter.model.Lobby;
import com.codecool.chatter.model.Room;
import com.codecool.chatter.view.AppView;
import com.codecool.chatter.view.LobbyView;
import com.codecool.chatter.view.RoomButton;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class LobbyController {

    private Socket connection;
    private Lobby lobby;
    private LobbyView lobbyView;
    private Room chosenRoom;

    private EventHandler<MouseEvent> enterRoom = e -> {
        RoomButton roomButton = (RoomButton) e.getSource();
        Room room = roomButton.getRoom();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(connection.getOutputStream());
            objectOutputStream.writeObject(room);
            objectOutputStream.flush();
            chosenRoom = room;
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    };


    public LobbyController(Socket connection) {
        this.connection = connection;
        lobby = null;
        chosenRoom = null;
        lobbyView = new LobbyView(ChatterClient.WIDTH, ChatterClient.HEIGHT);
    }


    public Room getChosenRoom() {
        return chosenRoom;
    }


    public void run(AppView appView) {
        try {
            getLobbyFromServer();
            lobbyView.renderLobbyView(lobby, enterRoom);
            appView.getChildren().add(lobbyView);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void getLobbyFromServer() throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(connection.getInputStream());
        lobby = (Lobby) inputStream.readObject();
        System.out.println("Lobby has been loaded!");
    }
}
