package com.codecool.chatter.view;

import javafx.scene.control.Button;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class ButtonView extends Button {

    public ButtonView(double width, double height) {
        super();
        setWidth(width);
        setHeight(height);
        setPrefSize(width, height);
    }


    public ButtonView getButton(String name, EventHandler<MouseEvent> onClick) {
        setFont(new Font(25));
        setText(name);
        setPrefHeight(25);
        setOnMouseClicked(onClick);
        return this;
    }
}
