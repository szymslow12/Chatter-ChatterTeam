package com.codecool.chatter.controller;

import com.codecool.chatter.model.Lobby;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class EchoThread extends Thread{

    private Socket socket;
    private AppController appController;

    public EchoThread(Socket socket, AppController appController) {
        this.socket = socket;
        this.appController = appController;
    }

    public void run() {

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            boolean userExist;
            do {
                userExist = appController.checkNickNameExist(objectInputStream);
                objectOutputStream.writeBoolean(userExist);
                objectOutputStream.flush();
            } while (!userExist);

            while (true) {
                Lobby lobby = appController.getLobby();
                objectOutputStream.writeObject(lobby);
                objectOutputStream.flush();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
