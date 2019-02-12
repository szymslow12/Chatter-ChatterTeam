package com.codecool.chatter.view;

import com.codecool.chatter.model.Lobby;
import com.codecool.chatter.model.Room;
import com.codecool.chatter.model.User;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LobbyInfoView extends GridPane {


    public LobbyInfoView(double width, double height, Insets insets) {
        setWidth(width);
        setHeight(height);
        setPrefSize(width, height);
        setPadding(insets);
        setHgap(insets.getTop());
        setVgap(insets.getTop());
    }

    public void renderLobbyInfoView(Lobby lobby, User client) {
        List<Room> rooms = lobby.getRooms();
        long roomsNumber = rooms.size();
        long usersNumber = IntStream.range(0, rooms.size()).mapToLong(i -> rooms.get(i).getUsers().size()).sum();
        Font font = new Font(25);
        Text userNameText = new Text("Username:");
        Text userName = new Text(client.getNickname());
        Text roomNumberText = new Text("Rooms:");
        Text roomNumber = new Text(Long.toString(roomsNumber));
        Text userNumberText = new Text("Users:");
        Text userNumber = new Text(Long.toString(usersNumber));
        setFont(font, userNameText, userName, roomNumberText, roomNumber, userNumberText, userNumber);
        setFill(Color.web("#000000"), roomNumberText, userNumberText, userNameText);
        setFill(Color.web("#00cc00"), roomNumber, userNumber, userName);
        setConstraints(userNameText, userName, roomNumberText, roomNumber, userNumberText, userNumber);
        getChildren().addAll(roomNumberText, roomNumber, userNumberText, userNumber, userNameText, userName);
    }


    private void setFill(Paint paint, Text... texts) {
        Stream.of(texts).forEach(text -> text.setFill(paint));
    }


    private void setFont(Font font, Text... texts) {
        Stream.of(texts).forEach(text -> text.setFont(font));
    }


    private void setConstraints(Text userNameText, Text userName, Text roomNumberText,
                                Text roomNumber, Text userNumberText, Text userNumber) {

        setConstraints(userNameText, 0, 0);
        setConstraints(userName, 1, 0);
        setConstraints(roomNumberText, 0, 2);
        setConstraints(roomNumber, 1, 2);
        setConstraints(userNumberText, 0, 4);
        setConstraints(userNumber, 1, 4);
    }
}
