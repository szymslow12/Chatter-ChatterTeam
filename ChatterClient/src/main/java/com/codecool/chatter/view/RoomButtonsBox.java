package com.codecool.chatter.view;

import com.codecool.chatter.model.Lobby;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class RoomButtonsBox extends HBox {

    private List<RoomButton> roomsButtons;
    private VBox vBox;
    private ScrollPane scrollPane;
    private ScrollBar scrollBar;

    public RoomButtonsBox(double width, double height) {
        super();
        roomsButtons = new ArrayList<>();
        renderVBox(width, height);
        renderScrollPane(vBox);
        renderScrollBar(scrollPane, vBox);
    }


    public void renderRoomButtonsBox(Lobby lobby, EventHandler<MouseEvent> onClick) {
        setAndAddRoomsButtons(vBox, lobby, onClick);
        setBorder(getBoxWithButtonBorder());
        getChildren().addAll(scrollBar, scrollPane);
    }


    private void setAndAddRoomsButtons(VBox box, Lobby lobby, EventHandler<MouseEvent> onClick) {
        lobby.getRooms().forEach(room -> roomsButtons.add(new RoomButton(getWidth() - 10, 100, room, onClick)));
        IntStream.range(0, roomsButtons.size()).forEach(i -> {
            RoomButton roomButton = roomsButtons.get(i);
            roomButton.setTranslateX(10);
        });
        roomsButtons.forEach(button -> box.getChildren().add(button));
    }


    private void renderVBox(double width, double height) {
        vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        setWidth(width);
        setHeight(height);
        VBox.setVgrow(vBox, Priority.ALWAYS);
    }


    private void renderScrollPane(VBox box) {
        scrollPane = new ScrollPane();
        scrollPane.setPrefSize(getWidth(), getHeight());
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(box);
    }


    private void renderScrollBar(ScrollPane scrollPane, VBox box) {
        scrollBar = new ScrollBar();
        scrollBar.setOrientation(Orientation.VERTICAL);
        scrollBar.minProperty().bind(scrollPane.vminProperty());
        scrollBar.maxProperty().bind(scrollPane.vmaxProperty());
        scrollBar.visibleAmountProperty().bind(scrollPane.heightProperty().divide(box.heightProperty()));
        scrollPane.vvalueProperty().bindBidirectional(scrollBar.valueProperty());
    }


    private Border getBoxWithButtonBorder() {
        Paint borderColor = Color.web("#000000");
        BorderStrokeStyle NONE = BorderStrokeStyle.NONE;
        BorderStrokeStyle SOLID = BorderStrokeStyle.SOLID;
        Insets insets = new Insets(0, 10, 0, 0);
        return new Border(new BorderStroke(borderColor, borderColor, borderColor, borderColor,
                NONE, SOLID, NONE, NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT, insets));
    }
}
