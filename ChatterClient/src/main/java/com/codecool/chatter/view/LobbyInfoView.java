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
        Text roomNumberText = new Text("Rooms:");
        Text roomNumber = new Text(Long.toString(roomsNumber));
        Text userNumberText = new Text("Users:");
        Text userNumber = new Text(Long.toString(usersNumber));
        setFont(font, roomNumberText, roomNumber, userNumberText, userNumber);
        setFill(Color.web("#000000"), roomNumberText, userNumberText);
        setFill(Color.web("#00cc00"), roomNumber, userNumber);
        setConstraints(roomNumberText, 0, 0);
        setConstraints(roomNumber, 1, 0);
        setConstraints(userNumberText, 0, 1);
        setConstraints(userNumber, 1, 1);
        getChildren().addAll(roomNumberText, roomNumber, userNumberText, userNumber);
    }


    private void setFill(Paint paint, Text... texts) {
        Stream.of(texts).forEach(text -> text.setFill(paint));
    }


    private void setFont(Font font, Text... texts) {
        Stream.of(texts).forEach(text -> text.setFont(font));
    }
}
