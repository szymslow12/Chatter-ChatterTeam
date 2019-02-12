package com.codecool.chatter.view;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CreateRoomView extends Pane {

    private InputField inputField;
    private ButtonView buttonView;
    private AlertMessage alertMessage;

    public CreateRoomView(double width, double height) {
        super();
        inputField = new InputField("Room name:", false, width * 0.9, height * 0.25, new Insets(10));
        buttonView = new ButtonView((width - 20) * 0.25, height * 0.1);
        alertMessage = new AlertMessage(width, height / 2, "Bad room name!");
        setWidth(width);
        setHeight(height);
        setPrefSize(width, height);
    }


    public InputField getInputField() {
        return inputField;
    }


    public void renderCreateRoomView(EventHandler<MouseEvent> onClick) {
        buttonView = buttonView.getButton("Create", onClick);
//        setBackground(
//            new Background(
//                new BackgroundFill(
//                        Color.web("rgba(35, 125, 195, 0.5)"),
//                        CornerRadii.EMPTY,
//                        Insets.EMPTY)
//            )
//        );
        setBorder(getCreateRoomBorder());
        setPositions();
        double x = (getWidth() - inputField.getWidth()) / 2;
        double y = 10;
        alertMessage.renderAlertMessage(x, y);
        getChildren().addAll(inputField, buttonView);
    }


    private void setPositions() {
        double middle = getHeight() / 2;
        inputField.setTranslateX(getInputMiddleX());
        inputField.setTranslateY(middle - inputField.getHeight() * 1.5);
        buttonView.setTranslateX(getButtonMiddleX());
        buttonView.setTranslateY(middle + buttonView.getHeight());
    }


    private double getInputMiddleX() {
        return (getWidth() / 2) - inputField.getWidth() / 2;
    }


    private double getButtonMiddleX() {
        return (getWidth() / 2) - buttonView.getWidth() / 2;
    }


    private Border getCreateRoomBorder() {
        Paint borderColor = Color.web("#000000");
        BorderStrokeStyle NONE = BorderStrokeStyle.NONE;
        BorderStrokeStyle SOLID = BorderStrokeStyle.SOLID;
        Insets insets = new Insets(0);
        return new Border(new BorderStroke(borderColor, borderColor, borderColor, borderColor,
                NONE, NONE, SOLID, NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT, insets));
    }


    public AlertMessage getBadRoomName() {
        return alertMessage;
    }
}
