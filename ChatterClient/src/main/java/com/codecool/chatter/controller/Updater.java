package com.codecool.chatter.controller;

import com.codecool.chatter.model.Connection;
import com.codecool.chatter.model.User;
import com.codecool.chatter.model.interfaces.Updatable;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;

import java.io.IOException;

public class Updater extends Thread {

    private Updatable updatable;
    private EventHandler<InputEvent> eventHandler;
    private Connection connection;
    private User client;
    private boolean isReceived;



    public Updater(User client, Connection connection) {
        this.client = client;
        this.connection = connection;
        isReceived = false;
        setName("Updater");
    }


    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }


    public void setEventHandler(EventHandler<InputEvent> eventHandler) {
        this.eventHandler = eventHandler;
    }


    public void setReceived(boolean isReceived) {
        this.isReceived = isReceived;
    }

    @Override
    public void run() {
        while (true) {
            if (isReceived) {
                Platform.runLater(() -> {
                    try {
                        updatable.updateView(connection.read(), client, eventHandler);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }
}
