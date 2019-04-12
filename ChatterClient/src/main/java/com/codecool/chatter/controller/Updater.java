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
    private volatile boolean isRunning;



    public Updater(Object object, Connection connection) {
        this.object = object;
        this.connection = connection;
        isRunning = true;
        setName("Updater");
    }


    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }


    public void setEventHandler(EventHandler<InputEvent> eventHandler) {
        this.eventHandler = eventHandler;
    }


    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }


    @Override
    public void run() {
        System.out.println("started Updater=" + this.getId() + "...");
        do {
            try {
//                System.out.println("Checks for access...");
//                Connection.waitForAccess(connection);
                System.out.println("Checks break conditions... isRunning=" + isRunning + " connection.isClosed()=" + connection.isClosed());
                if (!isRunning || connection.isClosed()) {
                    break;
                }
//                connection.setAvailable(false);
                System.out.println("Waits for response...");
                ObjectWrapper objectWrapper = connection.readUpdatedData();
                System.out.println("Updating View...");
                Platform.runLater(() -> {
                    updatable.updateView(objectWrapper, object, eventHandler);
                });
//                connection.setAvailable(true);
                System.out.println("Sleep for 50 milis");
                sleep(50);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (isRunning);
        System.out.println("stop Updater=" + this.getId() + "...");
    }
}
