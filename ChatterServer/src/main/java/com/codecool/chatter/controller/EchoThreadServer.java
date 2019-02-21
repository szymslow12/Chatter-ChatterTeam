package com.codecool.chatter.controller;

import com.codecool.chatter.model.*;

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
            connection.write(appController.wrapObject("lobby", lobby));

            chatHandle(user);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                removeUser(user);
                connection.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void removeUser(User user) {
        appController.removeClient(user);
        appController.getLobby().removeUser(user);
        if(user.getCurrentRoomId() != null) {
            Room room = appController.getRoomById(user.getCurrentRoomId());
            room.removeUser(user);
        }
    }

    private void chatHandle(User user) throws IOException, ClassNotFoundException {

        ObjectWrapper receiveData = connection.read();
        do{
            ObjectWrapper answer = appController.handleData(receiveData, user);
            connection.write(answer);
            receiveData = connection.read();
        }while (receiveData != null);

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
