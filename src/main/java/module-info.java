module com.example.final_2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires java.xml;


    opens gui to javafx.fxml;
    exports gui;
}