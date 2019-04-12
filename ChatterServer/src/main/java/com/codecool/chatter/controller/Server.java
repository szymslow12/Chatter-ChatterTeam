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
            DatagramSocket datagramSocket = new DatagramSocket();
            InetAddress inetAddress = InetAddress.getByName("localhost");
//            datagramSocket = new DatagramSocket(port);

            while (true) {

                Socket socket = serverSocket.accept();


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
