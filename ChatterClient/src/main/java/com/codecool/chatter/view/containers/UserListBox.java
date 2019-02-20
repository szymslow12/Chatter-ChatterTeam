package com.codecool.chatter.view.containers;

import com.codecool.chatter.model.Room;
import com.codecool.chatter.model.User;
import com.codecool.chatter.view.interactive.UserBox;

import java.util.ArrayList;
import java.util.List;

public class UserListBox extends ScrollContainer {

    private List<UserBox> userBoxes;


    public UserListBox(double width, double height) {
        super(width, height);
        this.userBoxes = new ArrayList<>();
    }


    public void renderUserListBox(Room room) {
        List<User> users = room.getUsers();
        users.stream().forEach(user -> addUserBoxToList(user));
        getChildren().addAll(getScrollPane(), getScrollBar());
        switchPositions();
    }


    private void addUserBoxToList(User user) {
        double width = getWidth() - 10;
        double height = 110;
        UserBox userBox = new UserBox(width, height, user);
        userBoxes.add(userBox);
        getVBox().getChildren().add(userBox);
    }


    public void clearChildren() {
        getChildren().clear();
        userBoxes.clear();
        getVBox().getChildren().clear();
    }
}
