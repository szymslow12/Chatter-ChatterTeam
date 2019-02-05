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
        Client client = new Client("localhost", 8080);
        primaryStage.setScene(client.getMainScene());
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Chatter Client");
        client.run();
        primaryStage.show();
    }
}
