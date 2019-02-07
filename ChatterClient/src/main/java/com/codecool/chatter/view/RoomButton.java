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
        setOnMouseClicked(onClick);
    }


    private void renderRoomButton() {
        setBackground(
            new Background(
                new BackgroundFill(
                    Color.web("rgba(0, 0, 0, 0.3)"),
                    CornerRadii.EMPTY,
                    Insets.EMPTY)
            )
        );
        Text text = new Text(room.getName());
        text.setFont(new Font(25));
        getChildren().add(text);
    }


    public Room getRoom() {
        return room;
    }
}
