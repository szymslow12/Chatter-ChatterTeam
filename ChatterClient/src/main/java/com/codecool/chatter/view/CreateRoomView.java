package com.codecool.chatter.view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class CreateRoomView extends Pane {

    private InputField inputField;
    private ButtonView buttonView;

    public CreateRoomView(double width, double height) {
        super();
        inputField = new InputField("Room name", false, width * 0.9, height * 0.25, new Insets(10));
        buttonView = new ButtonView((width - 20) * 0.25, height * 0.1);
        setWidth(width);
        setHeight(height);
        setPrefSize(width, height);
    }


    public void renderCreateRoomView(EventHandler<MouseEvent> onClick) {
        buttonView = buttonView.getButton("Create", onClick);
        setBackground(
            new Background(
                new BackgroundFill(
                        Color.web("rgba(35, 125, 195, 0.5)"),
                        CornerRadii.EMPTY,
                        Insets.EMPTY)
            )
        );
        setPositions();
        getChildren().addAll(inputField, buttonView);
    }


    private void setPositions() {
        double middle = getHeight() / 2;
        inputField.setTranslateX(getInputMiddleX());
        inputField.setTranslateY(middle - inputField.getHeight() * 1.5);
        buttonView.setTranslateX(getButtonMiddleX());
        buttonView.setTranslateY(middle + buttonView.getHeight());
    }


    private double getInputMiddleX() {
        return (getWidth() / 2) - inputField.getWidth() / 2;
    }


    private double getButtonMiddleX() {
        return (getWidth() / 2) - buttonView.getWidth() / 2;
    }
}
