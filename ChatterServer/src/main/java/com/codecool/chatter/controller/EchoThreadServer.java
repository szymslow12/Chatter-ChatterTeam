package com.codecool.chatter.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class EchoThreadServer extends Thread {

    private Socket socket;
    private AppController appController;

    public EchoThreadServer(Socket socket, AppController appController) {
        this.socket = socket;
        this.appController = appController;
    }

    public void run() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            while (true) {
                Object object = objectInputStream.readObject();
                appController.handleData(object);
//                objectOutputStream.writeObject();
                objectOutputStream.flush();
            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
