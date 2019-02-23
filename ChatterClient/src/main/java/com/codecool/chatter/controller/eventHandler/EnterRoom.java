package com.codecool.chatter.controller.eventHandler;

import com.codecool.chatter.controller.LobbyController;
import com.codecool.chatter.model.Connection;
import com.codecool.chatter.model.ObjectWrapper;
import com.codecool.chatter.model.Room;
import com.codecool.chatter.view.interactive.RoomButton;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.InputEvent;

import java.io.IOException;
import java.util.Optional;

public class EnterRoom implements EventHandler<InputEvent> {

    private LobbyController lobbyController;

    public EnterRoom(LobbyController lobbyController) {
        this.lobbyController = lobbyController;
    }

    @Override
    public void handle(InputEvent mouseEvent) {
        RoomButton roomButton = (RoomButton) mouseEvent.getSource();
        Room room = roomButton.getRoom();
        Alert confirm = getConfirmationAlert(room);
        Optional<ButtonType> confirmation = confirm.showAndWait();
        Connection connection = lobbyController.getConnection();
        if (confirmation.get() == ButtonType.OK) {
            try {
                Connection.waitForAccess(connection);
                connection.setAvailable(false);
                connection.write(new ObjectWrapper<>("chosenRoomId", room.getId()));
                setChosenRoom(connection);
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };


    private void setChosenRoom(Connection connection) throws IOException, ClassNotFoundException {
        ObjectWrapper objectWrapper = connection.read();
        while (!objectWrapper.getAction().equals("chosenRoomId")) {
            objectWrapper = connection.read();
        }
        connection.setAvailable(true);
        Room room = (Room) objectWrapper.getObject();
        lobbyController.setChosenRoom(room);
        lobbyController.getUpdater().setRunning(false);
    }


    private Alert getConfirmationAlert(Room room) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to join room '" + room.getName() + "' ?");
        return alert;
    }
}
