package com.codecool.chatter.model.interfaces;

import com.codecool.chatter.model.ObjectWrapper;
import com.codecool.chatter.model.User;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;

public interface Updatable {

    void updateView(ObjectWrapper objectWrapper, User client, EventHandler<InputEvent> eventHandler);
}
