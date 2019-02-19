package com.codecool.chatter.model.interfaces;

import com.codecool.chatter.model.ObjectWrapper;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;

public interface Updatable {

    void updateView(ObjectWrapper objectWrapper, Object object, EventHandler<InputEvent> eventHandler);
}
