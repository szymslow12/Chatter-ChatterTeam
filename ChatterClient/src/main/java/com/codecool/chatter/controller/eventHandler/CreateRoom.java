package com.codecool.chatter.controller.eventHandler;

import com.codecool.chatter.controller.LobbyController;
import com.codecool.chatter.model.Connection;
import com.codecool.chatter.model.ObjectWrapper;
import com.codecool.chatter.model.Room;
import com.codecool.chatter.view.LobbyView;
import com.codecool.chatter.view.box.CreateRoomForm;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class CreateRoom implements EventHandler<MouseEvent> {

    private LobbyController lobbyController;

    public CreateRoom(LobbyController lobbyController) {
        this.lobbyController = lobbyController;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        Connection connection = lobbyController.getConnection();
        LobbyView lobbyView = lobbyController.getLobbyView();
        CreateRoomForm createRoomForm = lobbyView.getCreateRoomForm();
        TextInputControl textInputControl = createRoomForm.getInputField().getTextInputControl();
        String roomName = textInputControl.getText();
        ObjectWrapper objectWrapper = new ObjectWrapper("createRoom", roomName);
        try {
            connection.write(objectWrapper);
            objectWrapper = connection.read();
            checkIfIsRoomNameAvailable(objectWrapper, createRoomForm);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    };


    private void checkIfIsRoomNameAvailable(ObjectWrapper objectWrapper, CreateRoomForm createRoomForm) {
        if (objectWrapper.getAction().equals("isAvailable")) {
            Canvas alert = createRoomForm.getAlertMessage();
            boolean isDisplayedAlert = createRoomForm.getChildren().contains(alert);
            if (!isDisplayedAlert) {
                createRoomForm.getChildren().add(alert);
            }
        } else {
            lobbyController.setChosenRoom((Room) objectWrapper.getObject());
        }
    }
}
