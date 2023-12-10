module com.example.calendar {
    requires javafx.controls;
    requires javafx.fxml;



    exports com.calendar.model;
    opens com.calendar.model to javafx.fxml;

    exports com.calendar.gui;
    opens com.calendar.gui to javafx.fxml;
}