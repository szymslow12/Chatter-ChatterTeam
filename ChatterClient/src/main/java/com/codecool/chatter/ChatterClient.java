package com.codecool.chatter;

import com.codecool.chatter.controller.Client;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatterClient extends Application {

    public static final double WIDTH = 1024;
    public static final double HEIGHT = WIDTH * 3 /4;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Client client = new Client("localhost", 8080, WIDTH, HEIGHT);
        Scene scene = client.getMainScene();
        scene.getStylesheets().add("css/main.css");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(WIDTH);
        primaryStage.setMinHeight(HEIGHT);
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Chatter Client");
        primaryStage.show();
        client.start();
    }
}
