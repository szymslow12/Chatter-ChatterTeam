package com.codecool.chatter.view.containers;

import com.codecool.chatter.model.Lobby;
import com.codecool.chatter.model.Room;
import com.codecool.chatter.view.interactive.RoomButton;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class RoomButtonBox extends ScrollContainer {

    private List<RoomButton> roomsButtons;

    public RoomButtonBox(double width, double height) {
        super(width, height);
        roomsButtons = new ArrayList<>();
    }


    public void renderRoomButtonsBox(Lobby lobby, EventHandler<InputEvent> onClick) {
        setAndAddRoomsButtons(lobby, onClick);
        getChildren().addAll(getScrollPane(), getScrollBar());
        switchPositions();
    }


    private void setAndAddRoomsButtons(Lobby lobby, EventHandler<InputEvent> onClick) {
        lobby.getRooms().forEach(room -> addRoomButtonToLst(room, onClick));
    }


    private void addRoomButtonToLst(Room room, EventHandler<InputEvent> onClick) {
        double width = getWidth() - 10;
        double height = 100;
        RoomButton roomButton = new RoomButton(width, height, room, onClick);
        roomButton.setTranslateX(5);
        roomsButtons.add(roomButton);
        getVBox().getChildren().add(roomButton);
    }


    public void clearChildren() {
        roomsButtons.clear();
        getChildren().clear();
        getVBox().getChildren().clear();
    }


    public void updateRoomButtons(Lobby lobby, EventHandler<InputEvent> onClick) {
        List<Room> rooms = lobby.getRooms();
        rooms.forEach(room -> {
            Optional<RoomButton> optional = getOptionalRoomButton(room);
            RoomButton roomButton;
            if (optional.isPresent()) {
                roomButton = optional.get();
                roomButton.update(room);
            } else {
                addRoomButtonToLst(room, onClick);
            }
        });
    }


    private Optional<RoomButton> getOptionalRoomButton(Room room) {
        Predicate<RoomButton> roomButtonPredicate = roomButton -> room.getName().equals(roomButton.getRoom().getName());
        return roomsButtons.stream().filter(roomButtonPredicate).findFirst();
    }
}
