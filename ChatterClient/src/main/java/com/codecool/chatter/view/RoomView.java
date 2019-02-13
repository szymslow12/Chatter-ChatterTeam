package com.codecool.chatter.view;

import com.codecool.chatter.model.Room;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class RoomView extends Pane {

    //private UserListBox userListBox;
    //private ChatBox chatBox;


    public RoomView(double width, double height) {
        super();
        setWidth(width);
        setHeight(height);
        setPrefSize(width, height);
    }


    public void renderRoomView(Room room) {
        setBackground(
            new Background(
                new BackgroundFill(
                        Color.web("rgba(255, 255, 255, 0.4)"),
                        CornerRadii.EMPTY,
                        Insets.EMPTY)
            )
        );
        //userListBox.renderUserListBox(room.getName(), room.getUsers());
        //chatBox.renderChatBox(room.getChat());
        //getChildren().addAll(userListBox, chatBox);
    }
}
