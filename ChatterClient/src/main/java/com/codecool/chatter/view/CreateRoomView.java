package com.codecool.chatter.view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class CreateRoomView extends GridPane {

    private InputField inputField;
    private ButtonView buttonView;

    public CreateRoomView(double width, double height) {
        super();
        inputField = new InputField("roomName", false, width - 20, height, new Insets(10));
        buttonView = new ButtonView((width - 20) * 0.25, height * 0.1);
        setWidth(width);
        setHeight(height);
    }


    public void renderCreateRoomView(EventHandler<MouseEvent> onClick) {
        buttonView = buttonView.getButton("Room name", onClick);
        getChildren().addAll(inputField, buttonView);
    }
}
