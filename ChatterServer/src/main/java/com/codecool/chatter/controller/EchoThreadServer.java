package com.codecool.chatter.controller;

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

            do {
                ObjectWrapper receiveData = (ObjectWrapper) objectInputStream.readObject();
                String action = receiveData.getAction();
                Object receiveObject = receiveData.getObject();
                Object answer = new ObjectWrapper(action, loginValidate(receiveObject)) ;
                objectOutputStream.writeObject(answer);
                objectOutputStream.flush();
            }while (user != null);

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

    private Object loginValidate(Object object) {
        String userName = (String) object;
        if(appController.checkNickNameExist(userName)){
            return false;
        }
        this.user = new User(userName);
        appController.getLobby().addUser(user);
        return appController.getLobby();
    }
}
