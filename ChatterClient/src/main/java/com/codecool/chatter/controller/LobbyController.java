package com.codecool.chatter.controller;

import com.codecool.chatter.ChatterClient;
import com.codecool.chatter.model.*;
import com.codecool.chatter.view.*;
import com.codecool.chatter.view.box.CreateRoomForm;
import com.codecool.chatter.view.interactive.RoomButton;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Optional;

public class LobbyController {

    private Connection connection;
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
                connection.write(new ObjectWrapper("chosenRoomId", room.getId()));
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


    private EventHandler<MouseEvent> createRoom = e -> {
        CreateRoomForm createRoomForm = lobbyView.getCreateRoomForm();
       TextInputControl textInputControl = createRoomForm.getInputField().getTextInputControl();
       String roomName = textInputControl.getText();
       ObjectWrapper objectWrapper = new ObjectWrapper("createRoom", roomName);
        try {
            connection.write(objectWrapper);
            objectWrapper = connection.read();
            if (objectWrapper.getAction().equals("isAvailable")) {
                Canvas alert = createRoomForm.getAlertMessage();
                boolean isDisplayedAlert = createRoomForm.getChildren().contains(alert);
                if (!isDisplayedAlert) {
                    createRoomForm.getChildren().add(alert);
                }
            } else {
                chosenRoom = (Room) objectWrapper.getObject();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

    };

    public LobbyController(Connection connection) {
        this.connection = connection;
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


    public void run(AppView appView, User client) {
        try {
            getLobbyFromServer();
            lobbyView.renderLobbyView(lobby, client, enterRoom, createRoom);
            appView.getChildren().add(lobbyView);
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
}
