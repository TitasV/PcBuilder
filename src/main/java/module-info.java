module com.example.pcbuilder2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens com.example.pcbuilder2 to javafx.fxml;
    exports com.example.pcbuilder2;
    exports com.example.pcbuilder2.fxControl;
    opens com.example.pcbuilder2.fxControl to javafx.fxml;
    exports com.example.pcbuilder2.model;
    opens com.example.pcbuilder2.model to javafx.fxml;
}