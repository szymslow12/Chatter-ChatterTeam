package com.codecool.chatter.model;

import java.io.*;

public class Connection {

    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;


    public Connection(OutputStream outputStream, InputStream inputStream) throws IOException {
        this.objectOutputStream = new ObjectOutputStream(outputStream);
        this.objectOutputStream.flush();
        this.objectInputStream = new ObjectInputStream(inputStream);
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
        Object object = objectInputStream.readObject();
        while (!(object instanceof ObjectWrapper)) {
            object = objectInputStream.readObject();
        }
        return (ObjectWrapper) object;
    }
}
