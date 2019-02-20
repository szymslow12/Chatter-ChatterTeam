package com.codecool.chatter.controller;

import com.codecool.chatter.model.Connection;
import com.codecool.chatter.model.ObjectWrapper;
import com.codecool.chatter.model.interfaces.Updatable;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;

import java.io.IOException;

public class Updater extends Thread {

    private Updatable updatable;
    private EventHandler<InputEvent> eventHandler;
    private Connection connection;
    private Object object;
    private volatile boolean isReceived;



    public Updater(Object object, Connection connection) {
        this.object = object;
        this.connection = connection;
        isReceived = true;
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
        System.out.println("started Updater...");
        try {
            while (true) {
                if (!isReceived) {
                    try {
                        ObjectWrapper objectWrapper = connection.read();
                        Platform.runLater(() -> {
                            updatable.updateView(objectWrapper, object, eventHandler);
                        });
                        sleep(50);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
