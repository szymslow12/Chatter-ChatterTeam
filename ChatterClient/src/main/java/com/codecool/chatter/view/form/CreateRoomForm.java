package com.codecool.chatter.view.form;

import com.codecool.chatter.view.alert.AlertMessage;
import com.codecool.chatter.view.interactive.ButtonView;
import com.codecool.chatter.view.interactive.InputField;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class CreateRoomForm extends Pane {

    private InputField inputField;
    private ButtonView buttonView;
    private AlertMessage alertMessage;

    public CreateRoomForm(double width, double height) {
        super();
        initializeField(width, height);
        setWidth(width);
        setHeight(height);
        setPrefSize(width, height);
    }


    public InputField getInputField() {
        return inputField;
    }


    public void renderCreateRoomView(EventHandler<MouseEvent> onClick) {
        buttonView = buttonView.getButton("Create", onClick);
        setBorder(getCreateRoomBorder());
        setPositions();
        renderAlert();
        //        setBackground(
//            new Background(
//                new BackgroundFill(
//                        Color.web("rgba(35, 125, 195, 0.5)"),
//                        CornerRadii.EMPTY,
//                        Insets.EMPTY)
//            )
//        );
        getChildren().addAll(inputField, buttonView);
    }


    private void initializeField(double width, double height) {
        inputField = new InputField(
            "Room name:",
            false,
            width * 0.9,
            height * 0.25,
            new Insets(10)
        );
        buttonView = new ButtonView(
            (width - 20) * 0.25,
            height * 0.1);
        alertMessage = new AlertMessage(
            inputField.getWidth(),
            inputField.getHeight() * 0.6,
            "Bad room name!");
    }


    private void setPositions() {
        inputField.setTranslateX(getInputMiddleX());
        inputField.setTranslateY(getInputY());
        buttonView.setTranslateX(getButtonMiddleX());
        buttonView.setTranslateY(getButtonY());
    }


    private double getInputMiddleX() {
        return (getWidth() / 2) - inputField.getWidth() / 2;
    }


    private double getInputY() {
        return (getHeight() / 2) - inputField.getHeight() * 1.5;
    }


    private double getButtonMiddleX() {
        return (getWidth() / 2) - buttonView.getWidth() / 2;
    }


    private double getButtonY() {
        return (getHeight() / 2)  + buttonView.getHeight();
    }


    private Border getCreateRoomBorder() {
        Paint borderColor = Color.web("#000000");
        BorderStrokeStyle NONE = BorderStrokeStyle.NONE;
        BorderStrokeStyle SOLID = BorderStrokeStyle.SOLID;
        Insets insets = new Insets(0);
        return new Border(new BorderStroke(borderColor, borderColor, borderColor, borderColor,
                NONE, NONE, SOLID, NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT, insets));
    }


    private void renderAlert() {
        double x = (getWidth() - inputField.getWidth()) / 2;
        double y = 5;
        alertMessage.renderAlertMessage(x, y);
    }


    public AlertMessage getAlertMessage() {
        return alertMessage;
    }
}
