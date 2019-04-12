package com.codecool.chatter.model;

import java.io.Serializable;

public class ObjectWrapper<T> implements Serializable {

    private String action;

    private T type;

    public ObjectWrapper(String action, T type) {
        this.action = action;
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public T getObject() {
        return type;
    }
}
