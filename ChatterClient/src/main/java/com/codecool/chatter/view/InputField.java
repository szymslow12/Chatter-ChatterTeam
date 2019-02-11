package com.codecool.chatter.view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class InputField extends GridPane {

    private String labelName;
    private Label label;
    private TextInputControl textInputControl;

    public InputField(String labelName, boolean isTextArea, double width, double height, Insets insets) {
        super();
        this.labelName = labelName;
        label =  new Label(labelName);
        textInputControl = getProperInputField(isTextArea);
        renderInputField(insets, width, height);
    }


    private void renderInputField(Insets insets, double width, double height) {
        Font font = new Font(25);
        setWidth(width);
        setHeight(height);
        setInputFieldStyles(label, textInputControl, insets, font);
    }


    private TextInputControl getProperInputField(boolean isTextArea) {
        if (isTextArea) {
            return new TextArea();
        } else {
            return new TextField();
        }
    }


    private void setInputFieldStyles(Label label, TextInputControl inputField, Insets insets, Font font) {
        label.setFont(font);
        label.setTextFill(Color.web("#000000"));
        inputField.setFont(font);
        setGapsAndSizes(insets);
        alignAndAdd(label, inputField);
        setConstraints(label, inputField);
    }


    private void setGapsAndSizes(Insets insets) {
        setPrefSize(getWidth(), getHeight());
        setPadding(insets);
        setVgap(insets.getTop());
        setHgap(insets.getLeft() * 2);
    }


    private void setConstraints(Label label, TextInputControl inputField) {
        setConstraints(label, 0 , 1);
        setConstraints(inputField, 1, 1);
    }


    private void alignAndAdd(Label label, TextInputControl inputField) {
        getChildren().addAll(label, inputField);
        setValignment(label, VPos.CENTER);
        setHalignment(label, HPos.RIGHT);
        setHalignment(inputField, HPos.CENTER);
    }


    public TextInputControl getTextInputControl() {
        return textInputControl;
    }
}
