package com.codecool.chatter.view;

import com.codecool.chatter.model.Room;
import javafx.scene.control.Button;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class ButtonView extends Button {

    private Long roomId;

    public ButtonView(double width, double height) {
        super();
        roomId = null;
        setWidth(width);
        setHeight(height);
        setPrefSize(width, height);
    }


    //TODO
    public ButtonView getRoomButton(Room room, EventHandler<MouseEvent> onClick) {
        setFont(new Font(15));
        setText(room.getName());
        roomId = room.getId();
        setOnMouseClicked(onClick);
        return this;
    }


    public ButtonView getButton(String name, EventHandler<MouseEvent> onClick) {
        setFont(new Font(25));
        setText(name);
        setPrefHeight(25);
        setOnMouseClicked(onClick);
        return this;
    }


    public Long getRoomId() {
        return roomId;
    }
}
