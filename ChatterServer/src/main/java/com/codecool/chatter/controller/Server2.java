package com.codecool.chatter.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {

    private int port;
    private AppController appController;

    public Server2(int port) {
        this.port = port;
        appController = new AppController();
    }

    public void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket;


        while (true) {
            socket = serverSocket.accept();
            new EchoThreadServer(socket, appController).start();
        }
    }
}
