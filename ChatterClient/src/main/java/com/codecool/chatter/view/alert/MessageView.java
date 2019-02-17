package com.codecool.chatter.view.alert;

import com.codecool.chatter.model.Message;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MessageView extends Canvas {

    public MessageView(double width, double height, Message message) {
        super(width, height);
        renderMessageView(message);
    }


    private void renderMessageView(Message message) {
        String messageString = getMessageString(message);
        GraphicsContext context = getGraphicsContext2D();
        Font font = new Font(15);
        Text text = new Text(messageString);
        text.setFont(font);
        context.setFont(font);
        context.fillText(messageString, 0, getHeight() - text.getLayoutBounds().getHeight() / 2);
    }


    private String getMessageString(Message message) {
        String author = message.getAuthor().getNickname();
        String content = message.getContent();
        String createdAt = message.getCreatedAt().toString();
        return String.format(">>%s<< %s - %s", author, createdAt, content);
    }
}
