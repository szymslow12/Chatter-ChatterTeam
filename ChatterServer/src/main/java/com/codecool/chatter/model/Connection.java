package com.codecool.chatter.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

public class Connection {

    private ByteArrayOutputStream bos;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private Socket socket;
    private DatagramSocket datagramSocket;
    private DatagramPacket packet;
    private byte[] buf = new byte[256];


    public Connection(Socket socket, DatagramPacket packet) throws IOException {
        this.packet = packet;
        this.socket = socket;
        datagramSocket = new DatagramSocket();
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.flush();
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        bos = new ByteArrayOutputStream();
    }

    public ObjectOutputStream getOutput() {
        return objectOutputStream;
    }


    public ObjectInputStream getInput() {
        return objectInputStream;
    }


    public void write(ObjectWrapper objectWrapper) throws IOException {
        objectOutputStream.writeObject(objectWrapper);
        objectOutputStream.reset();
        objectOutputStream.flush();
    }

    public void sendDataByUDPConnection(ObjectWrapper objectWrapper) throws IOException{
        InetAddress clientAdress = packet.getAddress();
        int clientPort = packet.getPort();
        bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(objectWrapper);
        buf = bos.toByteArray();
        DatagramPacket response = new DatagramPacket(buf, buf.length, clientAdress, clientPort);
        datagramSocket.send(response);
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
