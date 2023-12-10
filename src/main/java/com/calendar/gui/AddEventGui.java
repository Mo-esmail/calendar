/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calendar.gui;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.calendar.model.DayEvent;

/**
 *
 * @author lts
 */
public class AddEventGui extends GridPane {

    TimeSpinner spinner;
    TextField textFieldTile;
    TextArea textAreaNote;
    ColorPicker colorPicker;
    private Stage stage;
    private DayEvent dayEvent;
    private boolean delete = false;

    public AddEventGui(DayEvent dayEvent) {
        this.dayEvent = dayEvent;
        setHgap(10); //horizontal gap in pixels
        setVgap(10); //vertical gap in pixels
        Label timelabel = new Label("Time");
        spinner = new TimeSpinner();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        spinner.valueProperty().addListener((obs, oldTime, newTime)
                -> System.out.println(formatter.format(newTime)));

        Label title = new Label("Title");
        textFieldTile = new TextField();

        Label note = new Label("Note");
        textAreaNote = new TextArea();

        Label color = new Label("Color");
        colorPicker = new ColorPicker();

        Button addBtn = new Button("add or update");
        Button cancelBtn = new Button("cancel");
        Button deleteBtn = new Button("delete");
        // add on click action
        addBtn.setOnAction((e) -> {
            onAddClick();
        });
        setData(dayEvent);

        // add on click action
        cancelBtn.setOnAction((e) -> {
            stage.close();
        });
        // add on click action
        deleteBtn.setOnAction((e) -> {
            deleteEvent();
        });

        add(timelabel, 0, 0);
        add(spinner, 1, 0);

        add(title, 0, 1);
        add(textFieldTile, 1, 1);

        add(note, 0, 2);
        add(textAreaNote, 1, 2);

        add(color, 0, 3);
        add(colorPicker, 1, 3);

        add(addBtn, 0, 4);
        add(cancelBtn, 1, 4);
        add(deleteBtn, 2, 4);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public DayEvent getDayEvent() {
        return dayEvent;
    }

    public void setDayEvent(DayEvent dayEvent) {
        this.dayEvent = dayEvent;
    }

    private void onAddClick() {
        LocalTime time = spinner.getValue();
        String title = textFieldTile.getText();
        String note = textAreaNote.getText();
        String color = colorPicker.getValue().toString();
        dayEvent.setTime(time);
        dayEvent.setTitle(title);
        dayEvent.setNote(note);
        dayEvent.setColor(color);
        stage.close();
    }

    private void setData(DayEvent dayEvent) {
        if (dayEvent.getTime() != null) {
            spinner.getValueFactory().setValue(dayEvent.getTime());
            textFieldTile.setText(dayEvent.getTitle());
            textAreaNote.setText(dayEvent.getNote());
            colorPicker.setValue(Color.web(dayEvent.getColor()));
        }
    }

    private void deleteEvent() {
        if (dayEvent.getTime() != null) {
            delete = true;
            stage.close();
        }
    }

    /**
     * @return the delete
     */
    public boolean isDelete() {
        return delete;
    }
}
