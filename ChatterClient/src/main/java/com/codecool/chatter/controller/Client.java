package com.codecool.chatter.controller;

import com.codecool.chatter.controller.eventHandler.ExitProgram;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client {


    public static final double WIDTH = 1024;
    public static final double HEIGHT = WIDTH * 3 /4;
    private AppController appController;


    public Client(String host, int port, double width, double height) {
        this.appController = new AppController(host, port, width, height);
    }


    public void run(Stage primaryStage) {
        Scene scene = new Scene(appController.getAppView());
        scene.getStylesheets().add("css/main.css");
        setPrimaryStage(primaryStage, scene);
        primaryStage.show();
        appController.start();
    }


    private void setPrimaryStage(Stage primaryStage, Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(WIDTH);
        primaryStage.setMinHeight(HEIGHT);
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Chatter Client");
        primaryStage.setOnCloseRequest(appController.getExitProgram());
    }


    public Scene getMainScene() {
        return new Scene(appController.getAppView());
    }


    public ExitProgram getExitProgram() {
        return appController.getExitProgram();
    }
}
