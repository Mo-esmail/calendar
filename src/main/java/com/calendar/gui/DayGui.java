/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calendar.gui;

import java.util.Calendar;
import java.util.Date;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.calendar.model.DayEvent;
import com.calendar.model.DayModel;

/**
 *
 * @author lts
 */
public class DayGui extends GridPane {

    private Stage rootStatge;

    public DayGui(DayModel dayModel) {
        Label day = new Label();
        VBox hBox = new VBox();
        setPrefHeight(100);
        setPrefWidth(100);

        day.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(day, 0.0);
        AnchorPane.setRightAnchor(day, 0.0);

        setGridLinesVisible(true);
        day.setAlignment(Pos.CENTER);
        day.setText("" + getDayOfMonth(dayModel.getDateTime()));

        add(day, 0, 0);

        int c = 0, r = 0;
        ScrollPane scrollPane = new ScrollPane();
        Pane panel = new Pane();
        panel.setPrefSize(100, 100);
        panel.setClip(new Rectangle(panel.getPrefWidth(), panel.getPrefHeight()));
        panel.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                new BorderWidths(1))));

        for (DayEvent dayEvent : dayModel.getEvents()) {
            Label eventTile = new Label();
            eventTile.setText(dayEvent.getTitle());
            eventTile.setTextAlignment(TextAlignment.LEFT);
            eventTile.setTextFill(Color.web(dayEvent.getColor()));
            eventTile.setOnMouseClicked((e) -> {
                System.out.println("cliecked" + dayEvent.getNote());
                showAddEventGui(dayModel, dayEvent);
            });
            hBox.getChildren().add(eventTile);
        }
        panel.getChildren().add(hBox);
        scrollPane.setContent(panel);
        scrollPane.setPannable(true); // it means that the user should be able to pan the viewport by using the mouse.

        add(scrollPane, c, r + 1);
        Button btnAdd = new Button("add event");
        btnAdd.setOnAction((e) -> {
            showAddEventGui(dayModel, new DayEvent());

        });
        add(btnAdd, c, r + 2);
    }

    private void showAddEventGui(DayModel dayModel, DayEvent dayEvent) {
        Stage stage = new Stage();
        AddEventGui addEvent = new AddEventGui(dayEvent);
        addEvent.setStage(stage);
        stage.setScene(new Scene(addEvent));
        stage.initOwner(rootStatge);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
        dayEvent = addEvent.getDayEvent();
        int index = -1;
        for (int i = 0; i < dayModel.getEvents().size(); i++) {
            if (dayEvent.equals(dayModel.getEvents().get(i))) {
                index = i;
            }
        }
        if (addEvent.isDelete()) {
            dayModel.getEvents().remove(index);
             CalendarGUI.dataSet.update(dayModel);
        } else {
            if (dayEvent.getTitle() != null || !dayEvent.getTitle().trim().equals("")) {
                if (index != -1) {
                    dayModel.getEvents().set(index, dayEvent);
                } else {
                    dayModel.getEvents().add(dayEvent);
                }
                CalendarGUI.dataSet.update(dayModel);
            }
        }

    }

    public int getDayOfMonth(Date aDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * @return the rootStatge
     */
    public Stage getRootStatge() {
        return rootStatge;
    }

    /**
     * @param rootStatge the rootStatge to set
     */
    public void setRootStatge(Stage rootStatge) {
        this.rootStatge = rootStatge;
    }

}
