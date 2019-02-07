package com.codecool.chatter.view;

import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LoginView extends Pane {

    private InputField inputField;
    private ButtonView buttonView;
    private Canvas badLoginTry; // add to UML

    public LoginView(int width, int height, int margin, EventHandler<MouseEvent> onClick) {
        super();
        setWidth(width);
        setHeight(height);
        this.buttonView = new ButtonView(width * 0.15, 25).getButton("Join", onClick);
        createNicknameInputField(margin);
        renderBadLoginTryAlert();
        renderLoginView();
    }


    private void createNicknameInputField(int margin) {
        Insets margins = new Insets(margin, margin ,margin,margin);
        double width =  getWidth() * 0.75;
        double height = width * 0.25;
        this.inputField = new InputField("Nickname", false, width, height, margins);
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
        double x = getWidth() / 2;
        double y = getHeight() / 2;
        inputField.setLayoutX(x - inputField.getWidth() / 2);
        inputField.setLayoutY(y - inputField.getHeight() / 1.5);
        buttonView.setLayoutX(x - buttonView.getWidth() / 2);
        buttonView.setLayoutY(y + buttonView.getHeight());
    }


    public InputField getInputField() {
        return inputField;
    }


    public Canvas getBadLoginTry() {
        return badLoginTry;
    }


    private void renderBadLoginTryAlert() {
        double x = (getWidth() - inputField.getWidth()) / 2;
        double y = 10;
        badLoginTry = new Canvas(inputField.getWidth(), inputField.getHeight() / 2);
        drawBadLoginTry(badLoginTry);
        badLoginTry.setLayoutX(x);
        badLoginTry.setLayoutY(10);
    }


    private void drawBadLoginTry(Canvas canvas) {
        GraphicsContext context = canvas.getGraphicsContext2D();
        double width = inputField.getWidth();
        double height = inputField.getHeight() / 3;
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
        String badLoginMessage = "Nickname is in use!";
        Bounds bounds = new Text(badLoginMessage).getLayoutBounds();
        double textX = x + width / 2 - bounds.getWidth();
        double textY = y + ((height + 1) / 2) + bounds.getHeight() / 2;
        context.setFill(Color.web("#000000"));
        context.setFont(new Font(25));
        context.fillText(badLoginMessage, textX, textY);
    }
}
