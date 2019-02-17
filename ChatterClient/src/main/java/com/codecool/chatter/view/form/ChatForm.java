package com.codecool.chatter.view.form;

import com.codecool.chatter.model.Chat;
import com.codecool.chatter.view.containers.ChatBox;
import com.codecool.chatter.view.interactive.InputField;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;

public class ChatForm extends Pane {

    private ChatBox chatBox;
    private InputField inputField;

    public ChatForm(double width, double height) {
        setPrefSize(width, height);
        chatBox = new ChatBox(width, height * 0.8);
        inputField = new InputField("", true, width * 0.92, height * 0.2, new Insets(10));
    }


    public void renderChatForm(Chat chat) {
        chatBox.renderChatBox(chat);
        inputField.setTranslateY(chatBox.getHeight());
        getChildren().addAll(chatBox, inputField);
    }
}
