package com.codecool.chatter.view;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class LoginView extends Pane {

//    private InputField inputField;
//    private ButtonView buttonView;

    public LoginView() {
//        this.inputField = new InputField();
//        this.buttonView = new ButtonView();
        renderLoginView();
    }


    //TODO
    public void renderLoginView() {
        setBackground(
            new Background(
                new BackgroundFill(Color.web("#ffffff"), CornerRadii.EMPTY, Insets.EMPTY)
            )
        );
        setWidth(250d);
        setHeight(250d);
    }
}
