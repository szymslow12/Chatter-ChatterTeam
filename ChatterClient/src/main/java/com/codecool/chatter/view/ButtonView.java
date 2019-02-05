package com.codecool.chatter.view;

import com.codecool.chatter.model.Room;
import javafx.scene.control.Button;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class ButtonView extends Button {

    public ButtonView() {
        super();
    }


    //TODO
    public ButtonView getRoomButton(Room room) {
        return this;
    }


    public ButtonView getButton(String name, EventHandler<MouseEvent> onClick) {
        setFont(new Font(25));
        setText(name);
        setPrefHeight(25);
        setOnMouseClicked(onClick);
        return this;
    }
}
