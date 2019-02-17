package com.codecool.chatter.view.alert;

import com.codecool.chatter.model.Message;
import com.codecool.chatter.view.interactive.HoverPane;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.time.LocalDateTime;

public class MessageView extends HoverPane {

    public MessageView(double width, double height, Message message) {
        super(width, height, new Insets(10));
        renderMessageView(message);
    }


    private void renderMessageView(Message message) {
        String messageString = getMessageString(message);
        Font font = new Font(15);
        Text text = new Text(messageString);
        text.setFont(font);
        text.setLayoutX(10);
        resizeMessageSizeIfMessageIsLong(text);
        text.setLayoutY(getMiddleTextY(text));
        text.setWrappingWidth(getWidth());
        getChildren().add(text);
    }


    private void resizeMessageSizeIfMessageIsLong(Text message) {
        double messageWidth = message.getLayoutBounds().getWidth();
        double messageWidthDividedByContainerWidth = messageWidth / getWidth();
        if (messageWidthDividedByContainerWidth > 1) {
            double resizedHeight = getHeight() * messageWidthDividedByContainerWidth;
            setHeight(resizedHeight);
            setPrefHeight(resizedHeight);
        }
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


    private double getMiddleTextY(Text text) {
        return getHeight() / 2 - text.getLayoutBounds().getHeight() / 2;
    }
}
