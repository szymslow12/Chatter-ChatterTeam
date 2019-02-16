package com.codecool.chatter.view.interactive;

import com.codecool.chatter.model.User;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class UserBox extends Pane {

    private User user;

    public UserBox(double width, double height, User user) {
        super();
        this.user = user;
        setPrefSize(width, height);
        getChildren().add(getUserNameText(user));
        setTranslateX(5);
        setBackground(
                new Background(
                        new BackgroundFill(
                                Color.web("rgba(0, 0, 0, 0.4)"),
                                CornerRadii.EMPTY,
                                new Insets(10, 10, 10, 0))
                )
        );
        setHover(this);
    }


    private Text getUserNameText(User user) {
        Text userName = new Text(user.getNickname());
        userName.setFont(new Font(25));
        Bounds textBounds = userName.getLayoutBounds();
        userName.setLayoutY(getUserNameMiddleY());
        userName.setLayoutX(getUserNameMiddleX(textBounds));
        return userName;
    }


    private void setHover(Pane pane) {
        pane.setOnMouseEntered(event -> {
            pane.setBackground(
                    new Background(
                            new BackgroundFill(
                                    Color.web("rgba(0, 0, 0, 0.3)"),
                                    CornerRadii.EMPTY,
                                    new Insets(10, 10, 10, 0)
                            )
                    )
            );
        });
        pane.setOnMouseExited(event -> {
            pane.setBackground(
                    new Background(
                            new BackgroundFill(
                                    Color.web("rgba(0, 0, 0, 0.4)"),
                                    CornerRadii.EMPTY,
                                    new Insets(10, 10, 10, 0)
                            )
                    )
            );
        });
    }


    private double getUserNameMiddleY() {
        return (110d / 2) + 10;
    }


    private double getUserNameMiddleX(Bounds textBounds) {
        return ((getWidth()- 10) / 2) - (textBounds.getWidth() / 2) - 10;
    }
}
