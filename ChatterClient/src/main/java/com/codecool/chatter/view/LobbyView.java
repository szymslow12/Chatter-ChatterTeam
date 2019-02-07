package com.codecool.chatter.view;

import com.codecool.chatter.model.Lobby;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class LobbyView extends Pane {

//    private CreateRoomView createRoomView;
    private List<RoomButton> roomsButtons;

    public LobbyView(double width, double height) {
        super();
        roomsButtons = new ArrayList<>();
        setWidth(width);
        setHeight(height);
        setPrefSize(width, height);
    }


    public void renderLobbyView(Lobby lobby, EventHandler<MouseEvent> onClick) {
        setBackground(
            new Background(
                new BackgroundFill(
                        Color.web("rgba(255, 255, 255, 0.5)"),
                        CornerRadii.EMPTY,
                        Insets.EMPTY)
            )
        );
        lobby.getRooms().forEach(room -> roomsButtons.add(new RoomButton(400, 150, room, onClick)));
        IntStream.range(0, roomsButtons.size()).forEach(i -> {
            RoomButton roomButton = roomsButtons.get(i);
            roomButton.setTranslateY((roomButton.getHeight() * i + 10));
            roomButton.setTranslateX(10);
        });
        roomsButtons.forEach(button -> getChildren().add(button));
    }
}
