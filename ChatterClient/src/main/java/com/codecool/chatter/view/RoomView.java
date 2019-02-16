package com.codecool.chatter.view;

import com.codecool.chatter.ChatterClient;
import com.codecool.chatter.model.Room;
import com.codecool.chatter.view.containers.UserListBox;
import com.codecool.chatter.view.interactive.RoomButton;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class RoomView extends Pane {

    private UserListBox userListBox;
    //private ChatView chatBox;


    public RoomView(double width, double height) {
        super();
        userListBox = new UserListBox(300d, ChatterClient.HEIGHT - 100);
        setSizes(width, height);
    }


    private void setSizes(double width, double height) {
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
        RoomButton titleRoomButton = new RoomButton(userListBox.getWidth() + 30, 100, room);
        userListBox.renderUserListBox(room);
        userListBox.setTranslateY(100);
        //chatBox.renderChatBox(room.getChat());
//        getChildren().addAll(userListBox, chatBox);
        getChildren().addAll(titleRoomButton, userListBox);
    }
}
