package com.codecool.chatter.view;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class AppView extends Pane {

    //TODO
    public void renderAppView() {
        setBackground(
            new Background(
                new BackgroundFill(Color.web("#66ffff"), CornerRadii.EMPTY, Insets.EMPTY)
            )
        );
    }
}
