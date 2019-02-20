package com.codecool.chatter.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection {

    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private Socket socket;


    public Connection(Socket socket) throws IOException {
        this.socket = socket;
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
        objectOutputStream.reset();
        objectOutputStream.writeObject(objectWrapper);
        objectOutputStream.flush();
    }


    public ObjectWrapper read() throws IOException, ClassNotFoundException {
        return (ObjectWrapper) objectInputStream.readObject();
    }

    public void closeConnection() throws IOException {
        objectOutputStream.close();
        objectInputStream.close();
        socket.close();
    }
}
