package com.codecool.chatter.controller;

import com.codecool.chatter.model.Connection;

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
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);

            while (true) {
                Socket socket = serverSocket.accept();
                Connection connection = new Connection(socket);
                new EchoThreadServer(connection, appController).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
