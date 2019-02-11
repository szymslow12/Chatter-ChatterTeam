package com.codecool.chatter;

import com.codecool.chatter.controller.Server;

public class ChatterServer {

    public static void main(String[] args) {
        Server server = new Server(8080);
        server.startServer();
    }
}
