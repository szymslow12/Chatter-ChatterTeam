package com.codecool.chatter.view.interactive;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class HoverPane extends Pane {

    public HoverPane(double width, double height, Insets insets) {
        setWidth(width);
        setHeight(height);
        setPrefSize(width, height);
        setBackgroundAndHover(insets);
    }


    private void setBackgroundAndHover(Insets insets) {
        setBackground(
            new Background(
                new BackgroundFill(
                    Color.web("rgba(0, 0, 0, 0.4)"),
                    CornerRadii.EMPTY,
                    insets)
            )
        );
        setHover(insets);
    }


    private void setHover(Insets insets) {
        setOnMouseEntered(event ->
            setBackground(
                new Background(
                    new BackgroundFill(
                        Color.web("rgba(0, 0, 0, 0.3)"),
                        CornerRadii.EMPTY,
                        insets
                    )
                )
            )
        );
        setOnMouseExited(event ->
            setBackground(
                new Background(
                    new BackgroundFill(
                        Color.web("rgba(0, 0, 0, 0.4)"),
                        CornerRadii.EMPTY,
                        insets
                    )
                )
            )
        );
    }
}
