module com.example.prj {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.prj to javafx.fxml;
    exports com.example.prj;
}