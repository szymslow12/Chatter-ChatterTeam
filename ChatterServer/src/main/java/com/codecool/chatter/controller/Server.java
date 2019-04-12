package com.codecool.chatter.controller;

import com.codecool.chatter.model.Connection;

import java.io.IOException;
import java.net.*;

public class Server {

    private int port;
    private AppController appController;
    private byte[] buf = new byte[256];

    public Server(int port) {
        this.port = port;
        appController = new AppController();
    }

    public void startServer() {
        ServerSocket serverSocket = null;
//        DatagramSocket datagramSocket;

        try {
            serverSocket = new ServerSocket(port);
//            datagramSocket = new DatagramSocket(port);

            while (true) {

                Socket socket = serverSocket.accept();
                DatagramSocket datagramSocket = new DatagramSocket(8081);
                InetAddress inetAddress = InetAddress.getByName("localhost");

//                DatagramPacket packet = new DatagramPacket(buf, buf.length);
//                datagramSocket.receive(packet);

                Connection connection = new Connection(socket, datagramSocket, inetAddress);
                new EchoThreadServer(connection, appController).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert serverSocket != null;
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
