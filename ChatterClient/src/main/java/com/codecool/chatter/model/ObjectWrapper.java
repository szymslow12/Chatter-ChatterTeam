package com.codecool.chatter.model;

import java.io.Serializable;

public class ObjectWrapper implements Serializable {

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
