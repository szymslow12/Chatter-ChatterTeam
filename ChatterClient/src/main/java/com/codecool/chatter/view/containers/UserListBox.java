package com.codecool.chatter.view.containers;

import com.codecool.chatter.model.Room;
import com.codecool.chatter.view.interactive.RoomButton;

public class UserListBox extends LeftScrollContainer {


    public UserListBox(double width, double height) {
        super(width, height);
    }


    public void renderUserListBox(Room room) {
        getChildren().add(new RoomButton(getWidth(), getHeight(), room));
        System.out.println(room.getName() + " has " + room.getUsers().size() + " users...");
    }
}
