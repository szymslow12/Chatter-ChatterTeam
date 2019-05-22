package com.codecool.chatter.controller.eventHandler;

import com.codecool.chatter.controller.AppController;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class ExitProgram implements EventHandler<WindowEvent> {

    private AppController appController;

    public ExitProgram(AppController appController) {
        this.appController = appController;
    }

    @Override
    public void handle(WindowEvent windowEvent) {
        appController.getUpdater().setRunning(false);
        appController.setRunning(false);
        appController.setChosenRoom(null);
        appController.setClient(null);
    }
}
