package com.codecool.chatter;

import com.codecool.chatter.controller.Client;
import javafx.application.Application;
import javafx.stage.Stage;

public class ChatterClient extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Client client = new Client("localhost", 8080, Client.WIDTH, Client.HEIGHT);
        client.run(primaryStage);
    }
}
