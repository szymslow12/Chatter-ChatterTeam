package com.codecool.chatter.controller;

import com.codecool.chatter.model.Connection;
import com.codecool.chatter.model.Lobby;
import com.codecool.chatter.model.ObjectWrapper;
import com.codecool.chatter.model.User;

import java.io.IOException;

public class EchoThreadServer extends Thread {

    private Connection connection;
    private AppController appController;

    public EchoThreadServer(Connection connection, AppController appController) {
        this.appController = appController;
        this.connection = connection;
    }

    public void run() {
        User user = null;
        try {

            String userName;
            userName = loginHandle();
            user = new User(userName);

            appController.addClient(connection, user);
            appController.getLobby().addUser(user);
            Lobby lobby = appController.getLobby();
            System.out.println("lobby first refe: " + lobby);
            connection.write(appController.wrapObject("lobby", lobby));

            chatHandle(user);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.closeConnection();
                appController.removeClient(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void chatHandle(User user) throws IOException, ClassNotFoundException {
        while (true) {
            ObjectWrapper receiveData = connection.read();
            ObjectWrapper answer = appController.handleData(receiveData, user);
            connection.write(answer);
        }
    }

    private String loginHandle() throws IOException, ClassNotFoundException {
        ObjectWrapper receiveData;
        String action;
        String userName;
        Boolean userExist;
        do {
            receiveData = connection.read();
            action = receiveData.getAction();
            userName = (String) receiveData.getObject();
            userExist = !appController.checkNickNameExist(userName);
            connection.write(appController.wrapObject(action, userExist));
        } while (!userExist);

        return userName;
    }
}
