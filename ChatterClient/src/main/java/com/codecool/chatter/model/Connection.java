package com.codecool.chatter.model;

import java.io.*;

public class Connection {

    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private volatile boolean isAvailable;


    public Connection(OutputStream outputStream, InputStream inputStream) throws IOException {
        this.objectOutputStream = new ObjectOutputStream(outputStream);
        this.objectOutputStream.flush();
        this.objectInputStream = new ObjectInputStream(inputStream);
        isAvailable = true;
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


    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }


    public boolean isAvailable() {
        return isAvailable;
    }


    public ObjectWrapper read() throws IOException, ClassNotFoundException {
        Object object = objectInputStream.readObject();
        while (!(object instanceof ObjectWrapper)) {
            object = objectInputStream.readObject();
        }
        return (ObjectWrapper) object;
    }


    public static void waitForAccess(Connection connection) throws InterruptedException {
        while (!connection.isAvailable()) {
            Thread.sleep(10);
            if (connection.isAvailable()) {
                break;
            }
        }
    }


    public void close() throws IOException {
        objectOutputStream.close();
        objectInputStream.close();
    }
}
