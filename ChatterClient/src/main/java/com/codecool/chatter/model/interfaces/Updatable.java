package com.codecool.chatter.model.interfaces;

import com.codecool.chatter.model.ObjectWrapper;
import javafx.event.EventHandler;

public interface Updatable {

    void updateView(ObjectWrapper objectWrapper, EventHandler<?> eventHandler);
}
