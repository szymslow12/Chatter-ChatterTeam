package com.codecool.chatter.view.containers;

import com.codecool.chatter.model.User;
import javafx.scene.text.Text;

import java.util.List;

public class UserListBox extends LeftScrollContainer {


    public UserListBox(double width, double height) {
        super(width, height);
    }


    public void renderUserListBox(String roomName, List<User> users) {
        getChildren().add(new Text(roomName));
        System.out.println(roomName + " has " + users.size() + " users...");
    }
}
