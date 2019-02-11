package com.codecool.chatter.controller;

import com.codecool.chatter.ChatterClient;
import com.codecool.chatter.model.Lobby;
import com.codecool.chatter.model.ObjectWrapper;
import com.codecool.chatter.model.Room;
import com.codecool.chatter.view.AppView;
import com.codecool.chatter.view.LobbyView;
import com.codecool.chatter.view.RoomButton;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;

public class LobbyController {

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private Lobby lobby;
    private LobbyView lobbyView;
    private Room chosenRoom;

    private EventHandler<MouseEvent> enterRoom = e -> {
        RoomButton roomButton = (RoomButton) e.getSource();
        Room room = roomButton.getRoom();
        Alert confirm = getConfirmationAlert(room);
        Optional<ButtonType> confirmation = confirm.showAndWait();
        if (confirmation.get() == ButtonType.OK) {
            try {
                outputStream.writeObject(new ObjectWrapper("chosenRoomId", room.getId()));
                outputStream.flush();
                chosenRoom = room;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    };


    private Alert getConfirmationAlert(Room room) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to join room '" + room.getName() + "' ?");
        return alert;
    }


    public LobbyController(ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) {
        this.outputStream = objectOutputStream;
        this.inputStream = objectInputStream;
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
            lobbyView.renderLobbyView(lobby, enterRoom, enterRoom);
            appView.getChildren().add(lobbyView);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void getLobbyFromServer() throws IOException, ClassNotFoundException {
        lobby = (Lobby) ((ObjectWrapper) inputStream.readObject()).getObject();
        System.out.println("Lobby has been loaded!");
    }
}
