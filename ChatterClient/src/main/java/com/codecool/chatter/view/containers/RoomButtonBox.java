package com.codecool.chatter.view.containers;

import com.codecool.chatter.model.Lobby;
import com.codecool.chatter.view.interactive.RoomButton;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class RoomButtonBox extends ScrollContainer {

    private List<RoomButton> roomsButtons;

    public RoomButtonBox(double width, double height) {
        super(width, height);
        roomsButtons = new ArrayList<>();
    }


    public void renderRoomButtonsBox(Lobby lobby, EventHandler<MouseEvent> onClick) {
        setAndAddRoomsButtons(getVBox(), lobby, onClick);
        getChildren().addAll(getScrollPane(), getScrollBar());
        switchPositions();
    }


    private void setAndAddRoomsButtons(VBox box, Lobby lobby, EventHandler<MouseEvent> onClick) {
        lobby.getRooms().forEach(room -> roomsButtons.add(new RoomButton(getWidth() - 10, 100, room, onClick)));
        IntStream.range(0, roomsButtons.size()).forEach(i -> {
            RoomButton roomButton = roomsButtons.get(i);
            roomButton.setTranslateX(5);
        });
        roomsButtons.forEach(button -> box.getChildren().add(button));
    }


    public void clearChildren() {
        roomsButtons.clear();
        getChildren().clear();
        getVBox().getChildren().clear();
    }
}
