package com.codecool.chatter.view;

import com.codecool.chatter.ChatterClient;
import com.codecool.chatter.model.Lobby;
import com.codecool.chatter.model.ObjectWrapper;
import com.codecool.chatter.model.User;
import com.codecool.chatter.model.interfaces.Updatable;
import com.codecool.chatter.view.form.CreateRoomForm;
import com.codecool.chatter.view.containers.LobbyInfo;
import com.codecool.chatter.view.containers.RoomButtonBox;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class LobbyView extends Pane implements Updatable {

    private CreateRoomForm createRoomForm;
    private RoomButtonBox roomButtonBox;
    private LobbyInfo lobbyInfo;

    public LobbyView(double width, double height) {
        super();
        initializeFields(410d, ChatterClient.WIDTH - 430d, ChatterClient.HEIGHT);
        setSize(width, height);
    }


    public CreateRoomForm getCreateRoomForm() {
        return createRoomForm;
    }


    public void renderLobbyView(Lobby lobby, User client, EventHandler<InputEvent> roomOnClick, EventHandler<InputEvent> buttonOnClick) {
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
        lobbyInfo.renderLobbyInfoView(lobby, client);
        setPositions();
        getChildren().addAll(roomButtonBox, createRoomForm, lobbyInfo);
    }


    @Override
    public void updateView(ObjectWrapper objectWrapper, User client, EventHandler<InputEvent> eventHandler) {
        if (objectWrapper.getAction().equals("lobby")) {
            Lobby lobby = (Lobby) objectWrapper.getObject();
            getChildren().remove(roomButtonBox);
            getChildren().remove(lobbyInfo);
            recreateFields(410d, ChatterClient.WIDTH - 430d, ChatterClient.HEIGHT);
            rerenderView(lobby, client, eventHandler);
        }
    }


    private void initializeFields(double leftSiteWidth, double rightSiteWidth, double height) {
        roomButtonBox = new RoomButtonBox(leftSiteWidth, height);
        createRoomForm = new CreateRoomForm(rightSiteWidth, divide(height, 4));
        lobbyInfo = new LobbyInfo(
            rightSiteWidth,
            divide(height, 3f / 4),
            new Insets(10)
        );
    }


    private void recreateFields(double leftSiteWidth, double rightSiteWidth, double height) {
        roomButtonBox = new RoomButtonBox(leftSiteWidth, height);
        lobbyInfo = new LobbyInfo(rightSiteWidth, divide(height, 3f / 4), new Insets(10));
    }


    private void rerenderView(Lobby lobby, User client, EventHandler<InputEvent> eventHandler) {
        roomButtonBox.renderRoomButtonsBox(lobby, eventHandler);
        lobbyInfo.renderLobbyInfoView(lobby, client);
        setPositions();
        getChildren().addAll(roomButtonBox, lobbyInfo);
    }


    private void setSize(double width, double height) {
        setWidth(width);
        setHeight(height);
        setPrefSize(width, height);
    }


    private void setPositions() {
        createRoomForm.setLayoutX(roomButtonBox.getWidth() + 20);
        createRoomForm.setLayoutY(10);
        lobbyInfo.setLayoutX(roomButtonBox.getWidth() + 20);
        lobbyInfo.setLayoutY(createRoomForm.getHeight() + 10);
    }


    private double divide(double height, double parts) {
        return height / parts;
    }
}
