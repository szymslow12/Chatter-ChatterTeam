package com.codecool.chatter.view.containers;

import com.codecool.chatter.model.Room;
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

import java.util.List;
import java.util.stream.IntStream;

public class UserListBox extends LeftScrollContainer {


    public UserListBox(double width, double height) {
        super(width, height);
    }


    public void renderUserListBox(Room room) {
        List<User> users = room.getUsers();
        IntStream.range(0, users.size()).forEach(i -> getVBox().getChildren().add(renderUserBox(users.get(i))));
//        getScrollPane().setTranslateY(0);
        getChildren().addAll(getScrollBar(), getScrollPane());
        System.out.println(room.getName() + " has " + room.getUsers().size() + " users...");
    }


    private Pane renderUserBox(User user) {
        Pane pane = new Pane();
        Text userName = new Text(user.getNickname());
        userName.setFont(new Font(25));
        Bounds textBounds = userName.getLayoutBounds();
        userName.setLayoutY((110d / 2) + 10);
        userName.setLayoutX((getWidth() - 10) / 2 - (textBounds.getWidth() / 2));
        pane.setPrefSize(getWidth() - 10, 110);
        pane.getChildren().add(userName);
        pane.setTranslateX(10);
        pane.setBackground(
            new Background(
                new BackgroundFill(
                    Color.web("rgba(0, 0, 0, 0.4)"),
                    CornerRadii.EMPTY,
                    new Insets(10, 10, 10, 0))
            )
        );
        return pane;
    }
}
