package com.codecool.chatter.view;

import com.codecool.chatter.view.alert.AlertMessage;
import com.codecool.chatter.view.interactive.ButtonView;
import com.codecool.chatter.view.interactive.InputField;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class LoginView extends Pane {

    private InputField inputField;
    private ButtonView buttonView;
    private AlertMessage alertMessage;

    public LoginView(int width, int height, int margin, EventHandler<MouseEvent> onClick) {
        super();
        setWidth(width);
        setHeight(height);
        this.buttonView = new ButtonView(width * 0.15, 25).getButton("Join", onClick);
        createNicknameInputField(margin);
        renderAlertMessage();
        renderLoginView();
    }


    private void createNicknameInputField(int margin) {
        Insets margins = new Insets(margin, margin ,margin,margin);
        double width =  getWidth() * 0.75;
        double height = width * 0.25;
        this.inputField = new InputField(
            "Nickname",
            false,
            width,
            height,
            margins);
    }


    private void renderLoginView() {
        getChildren().addAll(inputField, buttonView);
        setPositionsOfElements();
        setBackground(
            new Background(
                new BackgroundFill(Color.web("#66ffff"), CornerRadii.EMPTY, Insets.EMPTY)
            )
        );
        setMinSize(getWidth(), getHeight());
    }


    private void setPositionsOfElements() {
        inputField.setLayoutX(getInputMiddleX());
        inputField.setLayoutY(getInputY());
        buttonView.setLayoutX(getButtonMiddleX());
        buttonView.setLayoutY(getButtonY());
    }


    private double getInputMiddleX() {
        return (getWidth() / 2) - inputField.getWidth() / 2;
    }


    private double getInputY() {
        return (getHeight() / 2) - inputField.getHeight() / 1.5;
    }


    private double getButtonMiddleX() {
        return (getWidth() / 2) - buttonView.getWidth() / 2;
    }


    private double getButtonY() {
        return (getHeight() / 2) + buttonView.getHeight();
    }


    public InputField getInputField() {
        return inputField;
    }


    public ButtonView getButtonView() {
        return buttonView;
    }


    public Canvas getAlertMessage() {
        return alertMessage;
    }


    private void renderAlertMessage() {
        double x = (getWidth() - inputField.getWidth()) / 2;
        double y = 20;
        alertMessage = new AlertMessage(
            inputField.getWidth(),
            inputField.getHeight() / 3,
            "Nickname is used by some else!");
        alertMessage.renderAlertMessage(x, y);
    }
}
