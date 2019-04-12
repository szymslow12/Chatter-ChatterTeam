package com.codecool.chatter.model;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Connection {

    private DatagramSocket datagramSocket;
    private InetAddress address;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private volatile boolean isAvailable;
    private boolean isClosed;


    public Connection(OutputStream outputStream, InputStream inputStream,
                      DatagramSocket datagramSocket, InetAddress address) throws IOException {

        this.datagramSocket = datagramSocket;
        this.address = address;
        this.objectOutputStream = new ObjectOutputStream(outputStream);
        this.objectOutputStream.flush();
        this.objectInputStream = new ObjectInputStream(inputStream);
        isAvailable = true;
        isClosed = false;
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


    public boolean isClosed() {
        return isClosed;
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
        datagramSocket.close();
        isClosed = true;
    }


    public ObjectWrapper readUpdatedData() throws IOException, ClassNotFoundException {
        byte[] buffer = new byte[5000];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        datagramSocket.receive(packet);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object object = objectInputStream.readObject();
        return (ObjectWrapper) object;

    }
}
