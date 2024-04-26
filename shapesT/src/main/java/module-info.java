module com.example.shapest {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.shapest to javafx.fxml;
    exports com.example.shapest;
}