/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calendar.gui;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.calendar.model.DataSet;
import com.calendar.model.DayModel;

/**
 *
 * @author lts
 */
public class MonthGui extends BorderPane {

    private Stage rootStatge;

    private String[] monthName = {"January", "Feburary", "March", "April", "May",
        "June", "July", "August", "September", "October", "November", "December"};
    // Maximum number of labels to display day names and days
    private GridPane[] lblDay = new GridPane[49];
    // The header label
    private Label lblHeader = new Label();

    private Calendar calendar;
    private int month;  // The specified month
    private int year;  // The specified year


    public MonthGui(int year, int month) {
        this.month = month;
        this.year = year;
        // Create labels for displaying days
        for (int i = 0; i < 49; i++) {
            lblDay[i] = new GridPane();
        }

        lblDay[0].add(new Label("Sunday"), 0, 0);
        lblDay[1].add(new Label("Monday"), 0, 1);
        lblDay[2].add(new Label("Tuesday"), 0, 2);
        lblDay[3].add(new Label("Wednesday"), 0, 3);
        lblDay[4].add(new Label("Thursday"), 0, 4);
        lblDay[5].add(new Label("Friday"), 0, 5);
        lblDay[6].add(new Label("Saturday"), 0, 6);

        GridPane dayPane = new GridPane();
//        dayPane.setAlignment(Pos.);

        // Place header and calendar body in the pane
        this.setTop(lblHeader);
        BorderPane.setAlignment(lblHeader, Pos.CENTER);

        // Set current month and year
        calendar = new GregorianCalendar();
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        updateCalendar();

        // Show calendar
        showHeader();
        showDays();

//        dayPane.setHgap(10);
//        dayPane.setVgap(10);
        for (int i = 0; i < 49; i++) {
            dayPane.setHgrow(lblDay[i], Priority.ALWAYS);
            dayPane.setVgrow(lblDay[i], Priority.ALWAYS);
            dayPane.add(lblDay[i], i % 7, i / 7);
        }

        dayPane.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                new BorderWidths(1))));
        setCenter(dayPane);
    }

    public void showHeader() {

        int monthIndex = month;
        if (monthIndex > 11) {
            monthIndex = 0;
        }
        lblHeader.setText(monthName[monthIndex] + ", " + year);
    }

    /**
     * Display days
     */
    public void showDays() {
        // Get the day of the first day in a month
        int startingDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);

        // Fill the calendar with the days before this month
        Calendar cloneCalendar = (Calendar) calendar.clone();
        cloneCalendar.add(Calendar.DATE, -1); // Becomes preceding month

        // befor on this month
        for (int i = 0; i < startingDayOfMonth - 1; i++) {
            lblDay[i + 7] = emptyPane();
        }

        // Display days of this month
        int daysInCurrentMonth = calendar.getActualMaximum(
                Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= daysInCurrentMonth; i++) {
            Date currentDate = new Date(year - 1900, month, i);
            DayModel dayModel = getDayModel(currentDate);
            if (dayModel == null) {
                dayModel = new DayModel();
                dayModel.setDateTime(currentDate);
            }
            DayGui dayGui = new DayGui(dayModel);
            dayGui.setRootStatge(rootStatge);
            lblDay[i - 2 + startingDayOfMonth + 7] = dayGui;
        }

        // Fill the calendar with the days after this month
        for (int i = daysInCurrentMonth - 1 + startingDayOfMonth + 7;
                i < 49; i++) {
            lblDay[i] = emptyPane();
        }

    }

    /**
     * Set the calendar to the first day of the specified month and year
     */
    public void updateCalendar() {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, 1);
    }

    /**
     * Return month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Set a new month
     */
    public void setMonth(int newMonth) {
        month = newMonth;
    }

    /**
     * Return year
     */
    public int getYear() {
        return year;
    }

    /**
     * Set a new year
     */
    public void setYear(int newYear) {
        year = newYear;
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

    private DayModel getDayModel(Date date) {
        DayModel dayModel = CalendarGUI.dataSet.searchByDate(date);
        return dayModel;
    }

    private GridPane emptyPane() {
        GridPane gridPane = new GridPane();
        gridPane.setPrefHeight(100);
        gridPane.setPrefWidth(100);
        gridPane.setGridLinesVisible(true);
        return gridPane;
    }
}
