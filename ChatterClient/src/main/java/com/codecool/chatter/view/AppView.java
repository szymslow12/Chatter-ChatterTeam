package com.codecool.chatter.view;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class AppView extends Pane {

    public AppView(double width, double height) {
        super();
        renderAppView(width, height);
    }


    private void renderAppView(double width, double height) {
        setPrefSize(width, height);
        setBackground(
            new Background(
                new BackgroundImage(new Image("background.jpg"),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT)
            )
        );
    }
}
