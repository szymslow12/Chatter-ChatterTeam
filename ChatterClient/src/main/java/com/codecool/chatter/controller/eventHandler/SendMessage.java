package com.codecool.chatter.controller.eventHandler;

import com.codecool.chatter.controller.RoomController;
import com.codecool.chatter.model.*;
import com.codecool.chatter.view.interactive.InputField;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class SendMessage implements EventHandler<KeyEvent> {

    private Connection connection;
    private RoomController roomController;
    private User client;

    public SendMessage(Connection connection, RoomController roomController, User client) {
        this.connection = connection;
        this.roomController = roomController;
        this.client = client;
    }



    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            InputField inputField = roomController.getRoomView().getChatForm().getInputField();
            TextInputControl textInputControl = inputField.getTextInputControl();
            String message = textInputControl.getText();
            try {
                Message toSend = new Message(client, message);
                connection.write(new ObjectWrapper("message", toSend));
                System.out.println("Message: '" + message + "' has been send...");
                roomController.getRoom().getChat().addMessage(toSend);
                roomController.updateChat();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
