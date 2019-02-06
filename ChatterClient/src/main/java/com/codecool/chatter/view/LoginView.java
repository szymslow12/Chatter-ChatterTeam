package com.codecool.chatter.view;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class LoginView extends Pane {

    private InputField inputField;
    private ButtonView buttonView;

    public LoginView(int width, int height, int margin, EventHandler<MouseEvent> onClick) {
        super();
        setWidth(width);
        setHeight(height);
        this.buttonView = new ButtonView().getButton("Join", onClick);
        createNicknameInputField(margin);
        renderLoginView();
    }


    private void createNicknameInputField(int margin) {
        Insets margins = new Insets(margin, margin ,margin,margin);
        double width =  getWidth() * 0.75;
        double height = width * 0.25;
        this.inputField = new InputField("Nickname", false, width, height, margins);
    }


    private void renderLoginView() {
        double x = getWidth() / 2;
        double y = getHeight() / 2;
        inputField.setLayoutX(x - inputField.getWidth() / 2);
        inputField.setLayoutY(y - inputField.getHeight() / 2);
        buttonView.setLayoutX(x - buttonView.getWidth() / 2);
//        buttonView.setLayoutY(y);
        getChildren().addAll(inputField, buttonView);
        setBackground(
            new Background(
                new BackgroundFill(Color.web("#66ffff"), CornerRadii.EMPTY, Insets.EMPTY)
            )
        );
        setMinSize(getWidth(), getHeight());
    }
}
