package com.codecool.chatter.view.form;

import com.codecool.chatter.model.Chat;
import com.codecool.chatter.view.containers.ChatBox;
import com.codecool.chatter.view.interactive.InputField;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class ChatForm extends Pane {

    private ChatBox chatBox;
    private InputField inputField;

    public ChatForm(double width, double height) {
        setPrefSize(width, height);
        chatBox = new ChatBox(width, height * 0.8);
        inputField = new InputField("", true, width * 0.92, height * 0.2, new Insets(10));
    }


    public void renderChatForm(Chat chat, EventHandler<KeyEvent> onEnter) {
        chatBox.renderChatBox(chat);
        setInputField(onEnter);
        getChildren().addAll(chatBox, inputField);
    }


    private void setInputField(EventHandler<KeyEvent> onEnter) {
        inputField.setTranslateY(chatBox.getHeight());
        inputField.setOnEnterPressed(onEnter);
        inputField.setFont(new Font(20));
        ((TextArea) inputField.getTextInputControl()).setWrapText(true);
    }


    public InputField getInputField() {
        return inputField;
    }


    public ChatBox getChatBox() {
        return chatBox;
    }


    public void updateChat(Chat chat) {
        chatBox.updateChat(chat);
    }


    public void clearChildren() {
        getChildren().clear();
        chatBox.clearChildren();
        inputField.getTextInputControl().clear();
    }
}
