package com.codecool.chatter.view.alert;

import com.codecool.chatter.model.Message;
import com.codecool.chatter.view.interactive.HoverPane;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.time.LocalDateTime;

public class MessageView extends HoverPane {

    private Message message;

    public MessageView(double width, double height, Message message) {
        super(width, height, new Insets(0));
        this.message = message;
        renderMessageView(message);
    }


    public Message getMessage() {
        return message;
    }

    private void renderMessageView(Message message) {
        String messageString = getMessageString(message);
        Text text = new Text(messageString);
        text.setFont(new Font(15));
        text.setLayoutX(10);
        resizeMessageSizeIfMessageIsLong(text);
        text.setWrappingWidth(getWidth() - 20);
        getChildren().add(text);
    }


    private void resizeMessageSizeIfMessageIsLong(Text message) {
        double messageWidth = message.getLayoutBounds().getWidth();
        double messageWidthDividedByContainerWidth = messageWidth / (getWidth() - 20);
        if (messageWidthDividedByContainerWidth > 1) {
            setSizeAndPosition(messageWidthDividedByContainerWidth, message);
        } else {
            message.setLayoutY(getMiddleTextY());
        }
    }


    private void setSizeAndPosition(double multiplier, Text message) {
        double messageHeight = message.getLayoutBounds().getHeight() * multiplier;
        setHeight(getHeight() + messageHeight);
        setPrefHeight(getHeight());
        message.setLayoutY(getPrefHeight() / 2 - messageHeight / 2 + 5);
    }


    private String getMessageString(Message message) {
        String author = message.getAuthor().getNickname();
        String content = message.getContent();
        String createdAt = getDate(message.getCreatedAt());
        return String.format(">>%s<< %s - %s", author, createdAt, content);
    }


    private String getDate(LocalDateTime date) {
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();
        int hour = date.getHour();
        int minute = date.getMinute();
        int second = date.getSecond();
        return String.format("%s-%s-%s %s:%s:%s", day, month, year, hour, minute, second);
    }


    private double getMiddleTextY() {
        return getHeight() / 2 + 5;
    }
}
