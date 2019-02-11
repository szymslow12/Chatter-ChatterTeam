package com.codecool.chatter.view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class CreateRoomView extends GridPane {

    private InputField inputField;
    private ButtonView buttonView;

    public CreateRoomView(double width, double height) {
        super();
        inputField = new InputField("Room name", false, width - 20, height, new Insets(10));
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
        getChildren().addAll(inputField, buttonView);
    }
}
