module com.example.prj {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.prj to javafx.fxml;
    exports com.example.prj;
}