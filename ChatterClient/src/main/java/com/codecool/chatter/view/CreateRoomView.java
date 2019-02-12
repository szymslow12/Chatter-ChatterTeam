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
    private Canvas badRoomName;

    public CreateRoomView(double width, double height) {
        super();
        inputField = new InputField("Room name:", false, width * 0.9, height * 0.25, new Insets(10));
        buttonView = new ButtonView((width - 20) * 0.25, height * 0.1);
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
        renderBadRoomNameAlert();
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


    public Canvas getBadRoomName() {
        return badRoomName;
    }


    private void renderBadRoomNameAlert() {
        double x = (getWidth() - inputField.getWidth()) / 2;
        double y = 10;
        badRoomName = new Canvas(inputField.getWidth(), inputField.getHeight() / 2);
        drawBadLoginTry(badRoomName);
        badRoomName.setLayoutX(x);
        badRoomName.setLayoutY(10);
    }


    private void drawBadLoginTry(Canvas canvas) {
        GraphicsContext context = canvas.getGraphicsContext2D();
        double width = inputField.getWidth();
        double height = inputField.getHeight() / 2;
        double x = 0; double y = 0;
        drawBox(x, y, width, height, context);
        drawMessage(x, y, width, height, context);
    }


    private void drawBox(double x, double y, double width, double height, GraphicsContext context) {
        context.strokeRect(x, y, width, height);
        context.setFill(Color.web("rgba(255, 0, 0, 0.8)"));
        context.fillRect(x + 1, y + 1, width - 1, height - 1);
    }


    private void drawMessage(double x, double y, double width, double height, GraphicsContext context) {
        String badLoginMessage = "Room name is used!";
        Bounds bounds = new Text(badLoginMessage).getLayoutBounds();
        double textX = x + width / 2 - bounds.getWidth();
        double textY = y + ((height + 1) / 2) + bounds.getHeight() / 2;
        context.setFill(Color.web("#000000"));
        context.setFont(new Font(25));
        context.fillText(badLoginMessage, textX, textY);
    }
}
