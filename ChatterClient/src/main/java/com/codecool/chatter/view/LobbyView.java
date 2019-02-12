package com.codecool.chatter.view;

import com.codecool.chatter.ChatterClient;
import com.codecool.chatter.model.Chat;
import com.codecool.chatter.model.Lobby;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class LobbyView extends Pane {

    private CreateRoomView createRoomView;
    private RoomButtonsBox roomButtonsBox;
    private LobbyInfoView lobbyInfoView;

    public LobbyView(double width, double height) {
        super();
        initializeFields(410d, ChatterClient.WIDTH - 430d, ChatterClient.HEIGHT);
        setSize(width, height);
    }


    private void initializeFields(double leftSiteWidth, double rightSiteWidth, double height) {
        roomButtonsBox = new RoomButtonsBox(leftSiteWidth, height);
        createRoomView = new CreateRoomView(rightSiteWidth, divide(height, 4));
        lobbyInfoView = new LobbyInfoView(rightSiteWidth, divide(height, 3 / 4), new Insets(10));
    }


    private void setSize(double width, double height) {
        setWidth(width);
        setHeight(height);
        setPrefSize(width, height);
    }


    public void renderLobbyView(Lobby lobby, EventHandler<MouseEvent> roomOnClick, EventHandler<MouseEvent> buttonOnClick) {
        setBackground(
            new Background(
                new BackgroundFill(
                    Color.web("rgba(255, 255, 255, 0.4)"),
                    CornerRadii.EMPTY,
                    Insets.EMPTY)
            )
        );
        roomButtonsBox.renderRoomButtonsBox(lobby, roomOnClick);
        createRoomView.renderCreateRoomView(buttonOnClick);
        lobbyInfoView.renderLobbyInfoView(lobby);
        createRoomView.setLayoutX(roomButtonsBox.getWidth() + 20);
        createRoomView.setLayoutY(10);
        lobbyInfoView.setLayoutX(roomButtonsBox.getWidth() + 20);
        lobbyInfoView.setLayoutY(createRoomView.getHeight() + 10);
        getChildren().addAll(roomButtonsBox, createRoomView, lobbyInfoView);
    }


    private double divide(double height, double parts) {
        return height / parts;
    }
}
