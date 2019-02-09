package com.codecool.chatter.view;

import com.codecool.chatter.ChatterClient;
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

public class LobbyView extends Pane {

//    private CreateRoomView createRoomView;
    private List<RoomButton> roomsButtons;
    private HBox boxWithButtons;

    public LobbyView(double width, double height) {
        super();
        roomsButtons = new ArrayList<>();
        setWidth(width);
        setHeight(height);
        setPrefSize(width, height);
    }


    public void renderLobbyView(Lobby lobby, EventHandler<MouseEvent> onClick) {
        setBackground(
            new Background(
                new BackgroundFill(
                    Color.web("rgba(255, 255, 255, 0.5)"),
                    CornerRadii.EMPTY,
                    Insets.EMPTY)
            )
        );
        addRoomsButtons(lobby, onClick);
    }


    private void addRoomsButtons(Lobby lobby, EventHandler<MouseEvent> onClick) {
        boxWithButtons = new HBox();
        VBox box = getVbox();
        setAndAddRoomsButtons(box, lobby, onClick);
        addScrollPaneAndScrollBarToBoxWithButtons(box);
        boxWithButtons.setBorder(getBoxWithButtonBorder());
        getChildren().add(boxWithButtons);
    }


    private void setAndAddRoomsButtons(VBox box, Lobby lobby, EventHandler<MouseEvent> onClick) {
        lobby.getRooms().forEach(room -> roomsButtons.add(new RoomButton(400, 100, room, onClick)));
        IntStream.range(0, roomsButtons.size()).forEach(i -> {
            RoomButton roomButton = roomsButtons.get(i);
            roomButton.setTranslateX(10);
        });
        roomsButtons.forEach(button -> box.getChildren().add(button));
    }


    private VBox getVbox() {
        VBox box = new VBox();
        box.setAlignment(Pos.TOP_CENTER);
        VBox.setVgrow(box, Priority.ALWAYS);
        return box;
    }


    private void addScrollPaneAndScrollBarToBoxWithButtons(VBox box) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(410d, ChatterClient.HEIGHT);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        ScrollBar vScrollBar = setAndGetScroll(scrollPane, box);
        scrollPane.setContent(box);
        boxWithButtons.getChildren().addAll(vScrollBar, scrollPane);
    }


    private ScrollBar setAndGetScroll(ScrollPane scrollPane, VBox box) {
        ScrollBar vScrollBar = new ScrollBar();
        vScrollBar.setOrientation(Orientation.VERTICAL);
        vScrollBar.minProperty().bind(scrollPane.vminProperty());
        vScrollBar.maxProperty().bind(scrollPane.vmaxProperty());
        vScrollBar.visibleAmountProperty().bind(scrollPane.heightProperty().divide(box.heightProperty()));
        scrollPane.vvalueProperty().bindBidirectional(vScrollBar.valueProperty());
        return vScrollBar;
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
