package com.codecool.chatter.view.containers;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ScrollContainer extends HBox {

    private VBox vBox;
    private ScrollPane scrollPane;
    private ScrollBar scrollBar;


    public ScrollContainer(double width, double height) {
        super();
        renderVBox(width, height);
        renderScrollPane(vBox);
        renderScrollBar(scrollPane, vBox);
        setBorder(getRightBorder());
    }


    public VBox getVBox() {
        return vBox;
    }


    public ScrollPane getScrollPane() {
        return scrollPane;
    }


    public ScrollBar getScrollBar() {
        return scrollBar;
    }


    private void renderVBox(double width, double height) {
        vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        setWidth(width);
        setHeight(height);
        VBox.setVgrow(vBox, Priority.ALWAYS);
    }


    private void renderScrollPane(VBox box) {
        scrollPane = new ScrollPane();
        scrollPane.setPrefSize(getWidth(), getHeight());
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(box);
    }


    private void renderScrollBar(ScrollPane scrollPane, VBox box) {
        scrollBar = new ScrollBar();
        scrollBar.setOrientation(Orientation.VERTICAL);
        scrollBar.minProperty().bind(scrollPane.vminProperty());
        scrollBar.maxProperty().bind(scrollPane.vmaxProperty());
        scrollBar.visibleAmountProperty().bind(scrollPane.heightProperty().divide(box.heightProperty()));
        scrollPane.vvalueProperty().bindBidirectional(scrollBar.valueProperty());
    }


    private Border getRightBorder() {
        Paint borderColor = Color.web("#000000");
        BorderStrokeStyle NONE = BorderStrokeStyle.NONE;
        BorderStrokeStyle SOLID = BorderStrokeStyle.SOLID;
        Insets insets = new Insets(0, 10, 0, 0);
        return new Border(new BorderStroke(borderColor, borderColor, borderColor, borderColor,
                NONE, SOLID, NONE, NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT, insets));
    }


    public void switchPositions() {
        scrollBar.setTranslateX(-(scrollPane.getPrefWidth()));
        scrollPane.setTranslateX(scrollBar.getWidth());
    }
}
