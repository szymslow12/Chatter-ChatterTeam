package com.codecool.chatter.view;

import com.codecool.chatter.ChatterClient;
import com.codecool.chatter.model.Lobby;
import com.codecool.chatter.model.User;
import com.codecool.chatter.view.box.CreateRoomForm;
import com.codecool.chatter.view.box.LobbyInfoBox;
import com.codecool.chatter.view.box.RoomButtonBox;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class LobbyView extends Pane {

    private CreateRoomForm createRoomForm;
    private RoomButtonBox roomButtonBox;
    private LobbyInfoBox lobbyInfoBox;

    public LobbyView(double width, double height) {
        super();
        initializeFields(410d, ChatterClient.WIDTH - 430d, ChatterClient.HEIGHT);
        setSize(width, height);
    }


    public CreateRoomForm getCreateRoomForm() {
        return createRoomForm;
    }


    public void renderLobbyView(Lobby lobby, User client, EventHandler<MouseEvent> roomOnClick, EventHandler<MouseEvent> buttonOnClick) {
        setBackground(
            new Background(
                new BackgroundFill(
                    Color.web("rgba(255, 255, 255, 0.4)"),
                    CornerRadii.EMPTY,
                    Insets.EMPTY)
            )
        );
        roomButtonBox.renderRoomButtonsBox(lobby, roomOnClick);
        createRoomForm.renderCreateRoomView(buttonOnClick);
        lobbyInfoBox.renderLobbyInfoView(lobby, client);
        setPositions();
        getChildren().addAll(roomButtonBox, createRoomForm, lobbyInfoBox);
    }


    private void initializeFields(double leftSiteWidth, double rightSiteWidth, double height) {
        roomButtonBox = new RoomButtonBox(leftSiteWidth, height);
        createRoomForm = new CreateRoomForm(rightSiteWidth, divide(height, 4));
        lobbyInfoBox = new LobbyInfoBox(
            rightSiteWidth,
            divide(height, 3f / 4),
            new Insets(10)
        );
    }


    private void setSize(double width, double height) {
        setWidth(width);
        setHeight(height);
        setPrefSize(width, height);
    }


    private void setPositions() {
        createRoomForm.setLayoutX(roomButtonBox.getWidth() + 20);
        createRoomForm.setLayoutY(10);
        lobbyInfoBox.setLayoutX(roomButtonBox.getWidth() + 20);
        lobbyInfoBox.setLayoutY(createRoomForm.getHeight() + 10);
    }


    private double divide(double height, double parts) {
        return height / parts;
    }
}
