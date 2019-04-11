package com.codecool.chatter.controller;

import com.codecool.chatter.controller.eventHandler.BackToLobby;
import com.codecool.chatter.controller.eventHandler.SendMessage;
import com.codecool.chatter.model.Connection;
import com.codecool.chatter.model.Room;
import com.codecool.chatter.model.User;
import com.codecool.chatter.view.AppView;
import com.codecool.chatter.view.RoomView;

public class RoomController extends Controller<Room> {

    public RoomController(Connection connection, Room room, Updater updater) {
        super(room, new RoomView(Client.WIDTH, Client.HEIGHT), connection, updater);
    }


    public void run(AppView appView, User client) {
        RoomView roomView = (RoomView) getUpdatable();
        Room room = getControlType();
        client.setCurrentRoomId(room.getId());
        room.getChat().setClient(client);
        roomView.renderRoomView(room, new SendMessage(this, client), new BackToLobby(this));
        appView.getChildren().add(roomView);
        startRoomUpdater(roomView);
    }


    private void startRoomUpdater(RoomView roomView) {
        Updater updater = getUpdater();
        updater.setUpdatable(roomView);
        updater.start();
    }
}
