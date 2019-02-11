package com.codecool.chatter.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private int port;
    private AppController appController;

    public Server(int port) {
        this.port = port;
        appController = new AppController();
    }

    public void startServer() {

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket;

            while (true) {
                socket = serverSocket.accept();
                new EchoThreadServer(socket, appController).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
