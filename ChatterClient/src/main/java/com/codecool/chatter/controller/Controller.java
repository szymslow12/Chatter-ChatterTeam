package com.codecool.chatter.controller;

import com.codecool.chatter.model.interfaces.Updatable;

public abstract class Controller<T> {

    private T controlType;
    private Updatable updatable;


    public T getControlType() {
        return controlType;
    }


    public void setControlType(T controlType) {
        this.controlType = controlType;
    }


    public Updatable getUpdatable() {
        return updatable;
    }


    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }

    public abstract void updateView();
}
