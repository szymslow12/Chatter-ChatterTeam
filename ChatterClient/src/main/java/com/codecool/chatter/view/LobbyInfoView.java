package com.codecool.chatter.view;

import com.codecool.chatter.model.Lobby;
import com.codecool.chatter.model.Room;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.stream.IntStream;

public class LobbyInfoView extends GridPane {


    public LobbyInfoView(double width, double height, Insets insets) {
        setWidth(width);
        setHeight(height);
        setPrefSize(width, height);
        setPadding(insets);
        setHgap(insets.getTop());
        setVgap(insets.getTop());
    }

    public void renderLobbyInfoView(Lobby lobby) {
        List<Room> rooms = lobby.getRooms();
        long roomsNumber = rooms.size();
        long usersNumber = IntStream.range(0, rooms.size()).mapToLong(i -> rooms.get(i).getUsers().size()).sum();
        System.out.println(roomsNumber + " - " + usersNumber);
    }
}
