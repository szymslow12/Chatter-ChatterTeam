package com.codecool.chatter.view.alert;

import com.codecool.chatter.model.Message;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class MessageView extends Canvas {

    public MessageView(double width, double height, Message message) {
        super(width, height);
        renderMessageView(message);
    }


    private void renderMessageView(Message message) {
        GraphicsContext context = getGraphicsContext2D();
        context.fillText(getMessageString(message), 0, 0);
    }


    private String getMessageString(Message message) {
        String author = message.getAuthor().getNickname();
        String content = message.getContent();
        String createdAt = message.getCreatedAt().toString();
        return String.format(">>%s<< %s - %s", author, createdAt, content);
    }
}
