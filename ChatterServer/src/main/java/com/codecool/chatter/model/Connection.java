package com.codecool.chatter.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.stream.Stream;

public class Connection {

    private ByteArrayOutputStream bos;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private Socket socket;
    private DatagramSocket datagramSocket;
    private InetAddress inetAddress;
//    private DatagramPacket packet;
    private byte[] buf = new byte[256];


    public Connection(Socket socket, DatagramSocket datagramSocket, InetAddress inetAddress) throws IOException {
//        this.packet = packet;
        this.socket = socket;
        this.datagramSocket = datagramSocket;
        this.inetAddress = inetAddress;
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
        bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(objectWrapper);
        buf = bos.toByteArray();
        DatagramPacket response = new DatagramPacket(buf, buf.length, inetAddress, 8081);
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
