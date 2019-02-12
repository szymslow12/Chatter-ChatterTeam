package com.codecool.chatter.view;

import javafx.geometry.Bounds;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AlertMessage extends Canvas {

    private String message;

    public AlertMessage(double width, double height, String message) {
        super(width, height);
        this.message = message;
    }


    public void renderAlertMessage(double x, double y) {
        GraphicsContext context = getGraphicsContext2D();
        drawBox(0, 0, getWidth(), getHeight(), context);
        drawMessage(0, 0, getWidth(), getHeight(), context);
        setLayoutX(x);
        setLayoutY(y);
    }


    private void drawBox(double x, double y, double width, double height, GraphicsContext context) {
        context.strokeRect(x, y, width, height);
        context.setFill(Color.web("rgba(255, 0, 0, 0.8)"));
        context.fillRect(x + 1, y + 1, width - 1, height - 1);
    }


    private void drawMessage(double x, double y, double width, double height, GraphicsContext context) {
        Bounds bounds = new Text(message).getLayoutBounds();
        double textX = x + width / 2 - bounds.getWidth();
        double textY = y + ((height + 1) / 2) + bounds.getHeight() / 2;
        context.setFill(Color.web("#000000"));
        context.setFont(new Font(25));
        context.fillText(message, textX, textY);
    }
}
