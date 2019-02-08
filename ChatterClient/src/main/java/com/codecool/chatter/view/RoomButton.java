package com.codecool.chatter.view;

import com.codecool.chatter.model.Room;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class RoomButton extends Pane {

    private Room room;

    public RoomButton(double width, double height, Room room, EventHandler<MouseEvent> onClick) {
        super();
        this.room = room;
        setWidth(width);
        setHeight(height);
        setPrefSize(width, height);
        renderRoomButton();
        addEventsListeners(onClick);
    }


    private void renderRoomButton() {
        setBackground(
            new Background(
                new BackgroundFill(
                    Color.web("rgba(0, 0, 0, 0.4)"),
                    CornerRadii.EMPTY,
                    new Insets(10, 10, 0, 0))
            )
        );
        Font font = new Font(25);
        Text roomName = getRoomName(font);
        double secondLineY = 50 + roomName.getLayoutBounds().getHeight();
        Text onlineUsers = getOnlineUsers(font, 10, secondLineY);
        Text onlineUsersNumber = getOnlineUsersNumber(font, onlineUsers, secondLineY);
        Text maxCapNum = getMaxCapNum(font, secondLineY);
        Text max = getMax(maxCapNum, font, secondLineY);
        getChildren().addAll(roomName, onlineUsers, onlineUsersNumber, maxCapNum, max);
    }


    private void addEventsListeners(EventHandler<MouseEvent> onClick) {
        setOnMouseClicked(onClick);
        setOnMouseEntered(event -> {
            setBackground(
                new Background(
                    new BackgroundFill(
                        Color.web("rgba(0, 0, 0, 0.3)"),
                        CornerRadii.EMPTY,
                        new Insets(10, 10, 0, 0)
                    )
                )
            );
        });
        setOnMouseExited(event -> {
            setBackground(
                new Background(
                    new BackgroundFill(
                        Color.web("rgba(0, 0, 0, 0.4)"),
                        CornerRadii.EMPTY,
                        new Insets(10, 10, 0, 0)
                    )
                )
            );
        });
    }


    private Text getRoomName(Font font) {
        Text roomName = new Text(room.getName());
        roomName.setFont(font);
        roomName.setTranslateX(getRoomNameX(roomName));
        roomName.setTranslateY(40);
        return roomName;
    }


    private double getRoomNameX(Text roomName) {
        return getWidth() / 2 - roomName.getLayoutBounds().getWidth() / 2;
    }


    private Text getOnlineUsers(Font font, double x, double y) {
        Text text = new Text("Online:");
        text.setFont(font);
        text.setTranslateX(x);
        text.setTranslateY(y);
        return text;
    }


    private Text getOnlineUsersNumber(Font font, Text onlineUsers, double y) {
        Text text = new Text(Integer.toString(room.getUsers().size()));
        text.setFont(font);
        text.setFill(Color.web("#00ff00"));
        text.setTranslateX(20 + onlineUsers.getLayoutBounds().getWidth());
        text.setTranslateY(y);
        return text;
    }


    private Text getMaxCapNum(Font font, double y) {
        Text maxCapNum = new Text(Integer.toString(room.getMaxCapacity()));
        maxCapNum.setFont(font);
        maxCapNum.setFill(Color.web("#ff0000"));
        maxCapNum.setTranslateX(getWidth() - 20 - maxCapNum.getLayoutBounds().getWidth());
        maxCapNum.setTranslateY(y);
        return maxCapNum;
    }


    private Text getMax(Text maxCapNum, Font font, double y) {
        Text max = new Text("Max:");
        max.setFont(font);
        max.setTranslateX(getWidth() - 20 - maxCapNum.getLayoutBounds().getWidth() - 10 - max.getLayoutBounds().getWidth());
        max.setTranslateY(y);
        return max;
    }


    public Room getRoom() {
        return room;
    }
}
