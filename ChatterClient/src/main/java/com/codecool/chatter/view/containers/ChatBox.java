package com.codecool.chatter.view.containers;

import com.codecool.chatter.model.Chat;
import com.codecool.chatter.model.Message;
import com.codecool.chatter.view.alert.MessageView;

import java.util.ArrayList;
import java.util.List;

public class ChatBox extends ScrollContainer {

    private List<MessageView> messages;

    public ChatBox(double width, double height) {
        super(width, height);
        messages = new ArrayList<>();
    }


    public void renderChatBox(Chat chat) {
        List<Message> messageList = chat.getMessages();
        messageList.stream().forEach(message -> addMessageView(message));
        getChildren().addAll(getScrollPane(), getScrollBar());
    }


    private void addMessageView(Message message) {
        MessageView messageView = new MessageView(getWidth() * 0.8, 50, message);
        messages.add(messageView);
        getVBox().getChildren().add(messageView);
    }
}
