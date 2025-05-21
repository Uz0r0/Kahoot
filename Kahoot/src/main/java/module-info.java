module com.example.kahoot {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.ooxml;


    opens com.example.kahoot to javafx.fxml;
    exports com.example.kahoot;
}