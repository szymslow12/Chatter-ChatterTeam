package com.codecool.chatter.controller;

import com.codecool.chatter.ChatterClient;
import com.codecool.chatter.controller.eventHandler.EnterRoom;
import com.codecool.chatter.model.*;
import com.codecool.chatter.view.*;
import com.codecool.chatter.view.box.CreateRoomForm;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class LobbyController {

    private Connection connection;
    private Lobby lobby;
    private LobbyView lobbyView;
    private Room chosenRoom;


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
            lobbyView.renderLobbyView(lobby, client, new EnterRoom(this), createRoom);
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
