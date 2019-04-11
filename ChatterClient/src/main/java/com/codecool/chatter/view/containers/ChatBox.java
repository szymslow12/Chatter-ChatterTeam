package com.codecool.chatter.view.containers;

import com.codecool.chatter.model.Chat;
import com.codecool.chatter.model.Message;
import com.codecool.chatter.view.alert.MessageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class ChatBox extends ScrollContainer {

    private List<MessageView> messages;

    public ChatBox(double width, double height) {
        super(width, height);
        messages = new ArrayList<>();
    }


    public void renderChatBox(Chat chat) {
        List<Message> messageList = chat.getMessages();
        messageList.stream().forEach(message -> addMessageView(message));
        setItemsSpacing(0);
        getScrollPane().setVvalue(1.0);
        getScrollBar().setVisible(false);
        getChildren().addAll(getScrollPane(), getScrollBar());
    }


    private void addMessageView(Message message) {
        MessageView messageView = new MessageView(getWidth() * 0.95, 50, message);
        messages.add(messageView);
        getVBox().getChildren().add(messageView);
    }


    public void clearChildren() {
        getChildren().clear();
        messages.clear();
        getVBox().getChildren().clear();
    }


    public void updateChat(Chat chat) {
        chat.getMessages().forEach(message -> {
            Optional<MessageView> optional = getOptionalMessageView(message);
            MessageView messageView;
            if (optional.isPresent()) {
                messageView = optional.get();
                messageView.update(message);
            } else {
                addMessageView(message);
            }
        });
    }


    private Optional<MessageView> getOptionalMessageView(Message message) {
        Predicate<MessageView> messageViewPredicate = messageView -> messageView.getMessage().equals(message);
        return messages.stream().filter(messageViewPredicate).findFirst();
    }
}
