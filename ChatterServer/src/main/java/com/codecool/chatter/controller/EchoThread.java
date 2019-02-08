package com.codecool.chatter.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class EchoThread extends Thread{

    private Socket socket;

    public EchoThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());


            while (true) {
                Object o = objectInputStream.readObject();
//                objectOutputStream.writeObject();
                objectOutputStream.flush();
            }
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
