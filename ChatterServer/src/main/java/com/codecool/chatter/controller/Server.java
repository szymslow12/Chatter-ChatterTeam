package com.codecool.chatter.controller;

import com.codecool.chatter.model.Lobby;
import com.codecool.chatter.model.Room;
import com.codecool.chatter.model.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Server {

    private int port;
    private List<User> users = new ArrayList<>();

    private List<Room> rooms = new ArrayList<>();

    private Lobby lobby;
//    private AppController appController;

    public Server(int port) {
        this.port = port;
        lobby = new Lobby();
        lobby.setRooms(rooms);
        lobby.setUsers(users);
        addDummyUsers(users);
        addDummyRooms(rooms);
    }


    //TODO
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept();
                User user = null;
                boolean isAvailable;
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.flush();
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                do {
                    String nickname = inputStream.readUTF();
                    isAvailable = users.stream().allMatch(
                        loggedUser -> !loggedUser.getNickname().equals(nickname));
                    outputStream.writeBoolean(isAvailable);
                    outputStream.flush();
                    System.out.println("Nickname '" + nickname + "' is " + (isAvailable ? "available": "not available"));
                    if (isAvailable)
                        user = new User(nickname);

                } while (!isAvailable);
                outputStream.writeObject(lobby);
                outputStream.flush();
                long chosenRoomId = inputStream.readLong();
                Room chosenRoom = getRoomById(chosenRoomId);
                if (chosenRoom == null) {
                    outputStream.writeObject(new IllegalArgumentException("Room does not exist!"));
                    outputStream.flush();
                } else {
                    System.out.println("User choose room " + chosenRoom.getName());
                    chosenRoom.addUser(user);
                    outputStream.writeObject(chosenRoom);
                    outputStream.flush();
                }
                long smthToStop = inputStream.readLong();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            run();
        }

    }


    private void addDummyUsers(List<User> users) {
        User john = new User("John");
        john.setCurrentRoomId(1l);
        User derp = new User("Derp");
        derp.setCurrentRoomId(1l);
        User simon = new User("Simon");

        users.addAll(Stream.of(john, derp, simon).collect(Collectors.toList()));
    }

    private void addDummyRooms(List<Room> rooms) {
        Room room1 = new Room(1, "dummyRoom_1", 8081);
        Room room2 = new Room(2, "dummyRoom_2", 8082);
        users.forEach(user -> room1.addUser(user));
        rooms.addAll(Stream.of(room1, room2).collect(Collectors.toList()));
    }


    private Room getRoomById(long id) {
        Optional<Room> optionalRoom = rooms.stream().filter(room -> room.getId() == id).findFirst();
        if (optionalRoom.isPresent()) {
            return optionalRoom.get();
        }
        return null;
    }
}