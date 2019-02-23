package com.codecool.chatter.controller.eventHandler;

import com.codecool.chatter.controller.LobbyController;
import com.codecool.chatter.model.Connection;
import com.codecool.chatter.model.ObjectWrapper;
import com.codecool.chatter.model.Room;
import com.codecool.chatter.view.LobbyView;
import com.codecool.chatter.view.form.CreateRoomForm;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.InputEvent;

import java.io.IOException;

public class CreateRoom implements EventHandler<InputEvent> {

    private LobbyController lobbyController;

    public CreateRoom(LobbyController lobbyController) {
        this.lobbyController = lobbyController;
    }

    @Override
    public void handle(InputEvent mouseEvent) {
        Connection connection = lobbyController.getConnection();
        LobbyView lobbyView = lobbyController.getLobbyView();
        CreateRoomForm createRoomForm = lobbyView.getCreateRoomForm();
        TextInputControl textInputControl = createRoomForm.getInputField().getTextInputControl();
        String roomName = textInputControl.getText();
        ObjectWrapper objectWrapper = new ObjectWrapper("createRoom", roomName);
        try {
            Connection.waitForAccess(connection);
            connection.setAvailable(false);
            connection.write(objectWrapper);
            objectWrapper = connection.read();
            while (!objectWrapper.getAction().equals("createRoom")) {
                objectWrapper = connection.read();
            }
            handleCreateRoom(objectWrapper, createRoomForm);
            connection.setAvailable(true);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };


    private void handleCreateRoom(ObjectWrapper objectWrapper, CreateRoomForm createRoomForm) {
        if (isNull(objectWrapper.getObject())) {
            handleAlert(createRoomForm);
        } else {
            lobbyController.setChosenRoom((Room) objectWrapper.getObject());
            lobbyController.getUpdater().setRunning(false);
        }
    }


    private void handleAlert(CreateRoomForm createRoomForm) {
        Canvas alert = createRoomForm.getAlertMessage();
        boolean isDisplayedAlert = createRoomForm.getChildren().contains(alert);
        if (!isDisplayedAlert) {
            createRoomForm.getChildren().add(alert);
        }
    }


    private boolean isNull(Object object) {
        return object == null;
    }
}
