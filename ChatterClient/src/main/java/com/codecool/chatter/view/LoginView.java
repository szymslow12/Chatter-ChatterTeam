package com.codecool.chatter.view;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class LoginView extends Pane {

    private InputField inputField;
    private ButtonView buttonView;
    private int width;
    private int height;



    public LoginView(int width, int height, int margin, EventHandler<MouseEvent> onClick) {
        super();
        this.width = width;
        this.height = height;
        this.buttonView = new ButtonView().getButton("Join", onClick);
        createNicknameInputField(margin);
        renderLoginView();
    }


    private void createNicknameInputField(int margin) {
        Insets margins = new Insets(margin, margin ,margin,margin);
        double width =  this.width * 0.75;
        double height = width * 0.25;
        this.inputField = new InputField("Nickname", false, width, height, margins);
    }


    private void renderLoginView() {
        getChildren().add(inputField);
        getChildren().add(buttonView);
        setBackground(
            new Background(
                new BackgroundFill(Color.web("#66ffff"), CornerRadii.EMPTY, Insets.EMPTY)
            )
        );
        setMinSize(width, height);
    }
}
