package com.codecool.chatter.controller;

import com.codecool.chatter.controller.eventHandler.BackToLobby;
import com.codecool.chatter.controller.eventHandler.SendMessage;
import com.codecool.chatter.model.Connection;
import com.codecool.chatter.model.Room;
import com.codecool.chatter.model.User;
import com.codecool.chatter.view.AppView;
import com.codecool.chatter.view.RoomView;

public class RoomController extends Controller<Room> {

    private Connection connection;
    private Updater updater;

    public RoomController(Connection connection, Room room, Updater updater) {
        this.connection = connection;
        setControlType(room);
        setUpdatable(new RoomView(Client.WIDTH, Client.HEIGHT));
        this.updater = updater;
    }


    public void run(AppView appView, User client) {
        RoomView roomView = (RoomView) getUpdatable();
        Room room = getControlType();
        client.setCurrentRoomId(room.getId());
        room.getChat().setClient(client);
        roomView.renderRoomView(room, new SendMessage(connection, this, client), new BackToLobby(this));
        appView.getChildren().add(roomView);
        startRoomUpdater(roomView);
    }


    private void startRoomUpdater(RoomView roomView) {
        updater.setUpdatable(roomView);
        updater.start();
    }
}
