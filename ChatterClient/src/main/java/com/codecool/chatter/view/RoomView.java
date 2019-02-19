package com.codecool.chatter.view;

import com.codecool.chatter.ChatterClient;
import com.codecool.chatter.model.Chat;
import com.codecool.chatter.model.ObjectWrapper;
import com.codecool.chatter.model.Room;
import com.codecool.chatter.model.User;
import com.codecool.chatter.model.interfaces.Updatable;
import com.codecool.chatter.view.containers.UserListBox;
import com.codecool.chatter.view.form.ChatForm;
import com.codecool.chatter.view.interactive.RoomButton;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class RoomView extends Pane implements Updatable {

    private UserListBox userListBox;
    private ChatForm chatForm;
    private RoomButton titleRoomButton;


    public RoomView(double width, double height) {
        super();
        userListBox = new UserListBox(300d, ChatterClient.HEIGHT - 100);
        chatForm = new ChatForm(ChatterClient.WIDTH - 300d, ChatterClient.HEIGHT);
        setSizes(width, height);
    }


    private void setSizes(double width, double height) {
        setWidth(width);
        setHeight(height);
        setPrefSize(width, height);
    }


    public void renderRoomView(Room room, EventHandler<KeyEvent> onEnter) {
        setBackground(
            new Background(
                new BackgroundFill(
                        Color.web("rgba(255, 255, 255, 0.4)"),
                        CornerRadii.EMPTY,
                        Insets.EMPTY)
            )
        );
        titleRoomButton = new RoomButton(userListBox.getWidth() + 30, 100, room);
        userListBox.renderUserListBox(room);
        chatForm.renderChatForm(room.getChat(), onEnter);
        setPositions();
        getChildren().addAll(titleRoomButton, userListBox, chatForm);
    }


    @Override
    public void updateView(ObjectWrapper objectWrapper, Object object, EventHandler<InputEvent> eventHandler) {
        if (objectWrapper.getAction().equals("updateRoom")) {
            Room updatedRoom = (Room) objectWrapper.getObject();
            Room roomChosen = (Room) object;
            roomChosen.setUsers(updatedRoom.getUsers());
            roomChosen.getChat().getMessages().addAll(updatedRoom.getChat().getMessages());
            getChildren().remove(titleRoomButton);
            getChildren().remove(userListBox);
            userListBox = new UserListBox(300d, ChatterClient.HEIGHT - 100);
            titleRoomButton = new RoomButton(userListBox.getWidth() + 30, 100, roomChosen);
            userListBox.renderUserListBox(roomChosen);
            setPositions();
            updateChat(roomChosen.getChat());
            getChildren().addAll(titleRoomButton, userListBox);

        }
    }


    private void setPositions() {
        userListBox.setTranslateY(100);
        chatForm.setTranslateX(userListBox.getWidth() + 30);
    }


    public void updateChat(Chat chat) {
        chatForm.updateChat(chat);
    }


    public ChatForm getChatForm() {
        return chatForm;
    }
}
