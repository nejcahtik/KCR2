module com.example.kcr2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kcr2 to javafx.fxml;
    exports com.example.kcr2;
}