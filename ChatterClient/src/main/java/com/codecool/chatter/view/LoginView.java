package com.codecool.chatter.view;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class LoginView extends Pane {

//    private InputField inputField;
//    private ButtonView buttonView;

    public LoginView() {
//        this.inputField = new InputField();
//        this.buttonView = new ButtonView();
        super();
        renderLoginView();
    }


    private void renderLoginView() {
        setBackground(
            new Background(
                new BackgroundFill(Color.web("#66ffff"), CornerRadii.EMPTY, Insets.EMPTY)
            )
        );
        setMinSize(750d, 500d);
    }
}
