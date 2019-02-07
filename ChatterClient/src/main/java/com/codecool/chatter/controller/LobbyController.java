package com.codecool.chatter.controller;

import com.codecool.chatter.ChatterClient;
import com.codecool.chatter.model.Lobby;
import com.codecool.chatter.model.Room;
import com.codecool.chatter.view.AppView;
import com.codecool.chatter.view.ButtonView;
import com.codecool.chatter.view.LobbyView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Optional;

public class LobbyController {

    private Socket connection;
    private Lobby lobby;
    private LobbyView lobbyView;
    private Room chosenRoom;

    private EventHandler<MouseEvent> enterRoom = e -> {
        ButtonView buttonView = (ButtonView) e.getSource();
        long chosenRoomId = buttonView.getRoomId();
        try {
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeLong(chosenRoomId);
            outputStream.flush();
            chosenRoom = getRoomFromLobby(chosenRoomId);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    };


    private Room getRoomFromLobby(long chosenId) {
        Optional<Room> optionalRoom = lobby.getRooms().stream().filter(room -> room.getId() == chosenId).findFirst();
        if (optionalRoom.isPresent()) {
            return optionalRoom.get();
        }
        return null;
    }

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
