package com.codecool.chatter.model;

import java.io.*;
import java.net.Socket;

public class Connection {

    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;


    public Connection(Socket socket) throws IOException {
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.flush();
        objectInputStream = new ObjectInputStream(socket.getInputStream());
    }

    public ObjectOutputStream getOutput() {
        return objectOutputStream;
    }


    public ObjectInputStream getInput() {
        return objectInputStream;
    }


    public void write(ObjectWrapper objectWrapper) throws IOException {
        objectOutputStream.writeObject(objectWrapper);
        objectOutputStream.flush();
    }


    public ObjectWrapper read() throws IOException, ClassNotFoundException {
        return (ObjectWrapper) objectInputStream.readObject();
    }

    public void closeConnection() throws IOException {
        objectOutputStream.close();
        objectInputStream.close();
    }
}
