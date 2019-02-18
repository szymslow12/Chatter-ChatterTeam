package com.codecool.chatter.view.interactive;

import com.codecool.chatter.model.User;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class UserBox extends HoverPane {

    private User user;

    public UserBox(double width, double height, User user) {
        super(width, height, new Insets(10, 10, 10, 0));
        this.user = user;
        renderUserBox();
    }


    private void renderUserBox() {
        setTranslateX(5);
        getChildren().add(getUserNameText());
    }


    private Text getUserNameText() {
        Text userName = new Text(user.getNickname());
        userName.setFont(new Font(25));
        Bounds textBounds = userName.getLayoutBounds();
        userName.setLayoutY(getUserNameMiddleY());
        userName.setLayoutX(getUserNameMiddleX(textBounds));
        return userName;
    }


    private double getUserNameMiddleY() {
        return (110d / 2) + 10;
    }


    private double getUserNameMiddleX(Bounds textBounds) {
        return ((getWidth()- 10) / 2) - (textBounds.getWidth() / 2) - 10;
    }
}
