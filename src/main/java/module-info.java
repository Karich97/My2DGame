module com.example.my2dgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.my2dgame to javafx.fxml;
    exports com.example.my2dgame;
}