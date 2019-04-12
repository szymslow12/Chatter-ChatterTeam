package com.codecool.chatter.controller.eventHandler;

import com.codecool.chatter.controller.Controller;
import com.codecool.chatter.model.*;
import com.codecool.chatter.view.RoomView;
import com.codecool.chatter.view.interactive.InputField;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class SendMessage implements EventHandler<KeyEvent> {

    private Controller<Room> roomController;
    private User client;

    public SendMessage(Controller<Room> roomController, User client) {
        this.roomController = roomController;
        this.client = client;
    }



    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            RoomView roomView = (RoomView) roomController.getUpdatable();
            InputField inputField = roomView.getChatForm().getInputField();
            TextInputControl textInputControl = inputField.getTextInputControl();
            String message = textInputControl.getText().trim();
            try {
                Message toSend = new Message(client, message);
                roomController.getConnection().write(new ObjectWrapper<>("message", toSend));
                roomController.getControlType().getChat().addMessage(toSend);
                textInputControl.clear();
                roomView.getChatForm().updateChat(roomController.getControlType().getChat());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
