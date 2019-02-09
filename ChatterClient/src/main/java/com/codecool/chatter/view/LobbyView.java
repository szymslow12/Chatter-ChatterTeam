package com.codecool.chatter.view;

import com.codecool.chatter.ChatterClient;
import com.codecool.chatter.model.Lobby;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class LobbyView extends Pane {

//    private CreateRoomView createRoomView;
    private RoomButtonsBox roomButtonsBox;

    public LobbyView(double width, double height) {
        super();
        roomButtonsBox = new RoomButtonsBox();
        setSize(width, height);
    }


    private void setSize(double width, double height) {
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
        roomButtonsBox.renderRoomButtonsBox(lobby, onClick);
        getChildren().add(roomButtonsBox);
    }
}
