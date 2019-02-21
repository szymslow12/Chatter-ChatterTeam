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
      //  System.out.println("connection.read() -> before reading");
        Object object = objectInputStream.readObject();
        //System.out.println("connection.read() -> After reading");
        while (!(object instanceof ObjectWrapper)) {
          //  System.out.println("connection.read() -> before reading in while");
            object = objectInputStream.readObject();
            //System.out.println("connection.read() -> after reading in while");
        }
        //System.out.println("connection.read() -> casting and return");
        return (ObjectWrapper) object;
    }
}
