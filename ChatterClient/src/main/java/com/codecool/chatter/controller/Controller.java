package com.codecool.chatter.controller;

public abstract class Controller<T> {

    private T controlType;


    public T getControlType() {
        return controlType;
    }


    public void setControlType(T controlType) {
        this.controlType = controlType;
    }
}
