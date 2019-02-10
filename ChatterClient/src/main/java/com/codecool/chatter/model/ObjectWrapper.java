package com.codecool.chatter.model;

public class ObjectWrapper {

    private String action;

    private Object object;

    public ObjectWrapper(String action, Object object) {
        this.action = action;
        this.object = object;
    }

    public String getAction() {
        return action;
    }

    public Object getObject() {
        return object;
    }
}
