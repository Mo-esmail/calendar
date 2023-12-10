/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calendar.gui;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import com.calendar.model.DataSet;

public class CalendarGUI extends Application {

    Stage primaryStage;

    Button btPrior = new Button("Previous");
    Button btNext = new Button("Next"); 
    
    Calendar calendar = new GregorianCalendar();
    MonthGui calendarPane = new MonthGui(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
    BorderPane pane = new BorderPane();
    HBox hBox = new HBox(5);

    public static DataSet dataSet = new DataSet();

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Calendar"); // Set the stage title
        primaryStage.setScene(creatScene()); // Place the scene in the stage
        primaryStage.show(); // Display the stage

    }
   

    private Scene creatScene() {
        calendarPane.setRootStatge(primaryStage);
        pane.setCenter(calendarPane);

        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(btPrior, btNext);
        pane.setBottom(hBox);
        hBox.setAlignment(Pos.CENTER);
        btPrior.setOnAction(e -> {
            int currentMonth = calendarPane.getMonth();
            int currentYear = calendarPane.getYear();
            // 0 = jan
            if (currentMonth == 0) { // The previous month is 11 for Dec
                calendarPane = new MonthGui(currentYear - 1, 11);
            } else {
                calendarPane = new MonthGui(currentYear, (currentMonth - 1) % 13);
            }
            calendarPane.setRootStatge(primaryStage);
            pane.setCenter(calendarPane);
            this.primaryStage.getScene().setRoot(pane);
        });

        btNext.setOnAction(e -> {

            int currentMonth = calendarPane.getMonth();
            int currentYear = calendarPane.getYear();
            if (currentMonth == 11) // The next month is 0 for Jan
            {
                currentYear += 1;
            }
            calendarPane = new MonthGui(currentYear, (currentMonth + 1) % 13);
            calendarPane.setRootStatge(primaryStage);
            pane.setCenter(calendarPane);
            this.primaryStage.getScene().setRoot(pane);

        });

        // Create a scene and place it in the stage
        return new Scene(pane);
    }

    /**
     * The main method is only needed for the IDE with limited JavaFX support.
     * Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
