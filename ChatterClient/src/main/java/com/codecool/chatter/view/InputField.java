package com.codecool.chatter.view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class InputField extends GridPane {

    private String name;

    public InputField(String name, boolean isTextArea, double width, double height, Insets insets) {
        super();
        setWidth(width);
        setHeight(height);
        this.name = name;
        renderInputField(isTextArea, insets);
    }


    private void renderInputField(boolean isTextArea, Insets insets) {
        Font font = new Font(25);
        Label label = new Label(name);
        TextInputControl inputField = getProperInputField(isTextArea);
        setInputFieldStyles(label, inputField, insets, font);
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


    private void setConstraints(Label label, Node inputField) {
        setConstraints(label, 0 , 1);
        setConstraints(inputField, 1, 1);
    }


    private void alignAndAdd(Node label, Node inputField) {
        getChildren().addAll(label, inputField);
        setValignment(label, VPos.CENTER);
        setHalignment(label, HPos.RIGHT);
        setHalignment(inputField, HPos.CENTER);
    }
}
