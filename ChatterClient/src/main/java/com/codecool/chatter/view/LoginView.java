package com.codecool.chatter.view;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class LoginView extends Pane {

    private InputField inputField;
//    private ButtonView buttonView;
    private int width;
    private int height;

    public LoginView(int width, int height) {
        super();
        this.width = width;
        this.height = height;
        createNicknameInputField();
//        this.buttonView = new ButtonView();
        renderLoginView();
    }


    private void createNicknameInputField() {
        Insets margins = new Insets(25, 25 ,25 ,25);
        double width =  this.width * 0.75;
        double height = width * 0.75;
        this.inputField = new InputField("Nickname", false, width, height, margins);
    }


    private void renderLoginView() {
        getChildren().add(inputField);
        setBackground(
            new Background(
                new BackgroundFill(Color.web("#66ffff"), CornerRadii.EMPTY, Insets.EMPTY)
            )
        );
        setMinSize(width, height);
    }
}
