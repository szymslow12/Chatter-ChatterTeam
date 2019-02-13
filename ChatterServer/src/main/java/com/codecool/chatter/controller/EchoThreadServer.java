package com.codecool.chatter.controller;

import com.codecool.chatter.model.Lobby;
import com.codecool.chatter.model.ObjectWrapper;
import com.codecool.chatter.model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class EchoThreadServer extends Thread {

    private Socket socket;
    private AppController appController;
    private User user;

    public EchoThreadServer(Socket socket, AppController appController) {
        this.socket = socket;
        this.appController = appController;
    }

    public void run() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            ObjectWrapper receiveData;
            String action;
            Object receiveObject;
            Boolean userExist;
            do {
                receiveData = (ObjectWrapper) objectInputStream.readObject();
                action = receiveData.getAction();
                receiveObject = receiveData.getObject();
                userExist = loginValidate(receiveObject);
                objectOutputStream.writeObject(new ObjectWrapper(action, userExist));
                objectOutputStream.flush();
            }while (userExist);

            String userName = (String) receiveObject;
            this.user = new User(userName);
            Lobby lobby = appController.getLobby();
            lobby.addUser(user);

            objectOutputStream.writeObject(appController.wrapObject("lobby", appController.getLobby()));
            objectOutputStream.flush();

            while (true) {
                Object object = objectInputStream.readObject();
                Object answer = appController.handleData(object, user);
                objectOutputStream.writeObject(answer);
                objectOutputStream.flush();
            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private boolean loginValidate(Object object) {
        String userName = (String) object;
        return !appController.checkNickNameExist(userName);
    }
}
