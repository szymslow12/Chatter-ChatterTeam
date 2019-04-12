package com.codecool.chatter.controller;

import com.codecool.chatter.model.Connection;
import com.codecool.chatter.model.interfaces.Updatable;

public abstract class Controller<T> {

    private T controlType;
    private Updatable updatable;
    private Connection connection;
    private Updater updater;

    Controller(T controlType, Updatable updatable, Connection connection, Updater updater) {
        this.controlType = controlType;
        this.updatable = updatable;
        this.connection = connection;
        this.updater = updater;
    }

    public T getControlType() {
        return controlType;
    }


    public void setControlType(T controlType) {
        this.controlType = controlType;
    }


    public Updatable getUpdatable() {
        return updatable;
    }


    public Connection getConnection() {
        return connection;
    }


    public Updater getUpdater() {
        return updater;
    }
}
