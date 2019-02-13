package com.codecool.chatter.view;

import com.codecool.chatter.model.Room;
import javafx.scene.layout.Pane;

public class RoomView extends Pane {


    public RoomView(double width, double height) {
        super();
        setWidth(width);
        setHeight(height);
        setPrefSize(width, height);
    }


    public void renderRoomView(Room room) {

    }
}
