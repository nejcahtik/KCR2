package com.example.kcr2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

public class PriceController {

    @FXML
    private Text rentalprice;
    @FXML
    private Rectangle rect;

    @FXML
    private void initialize() {
        rentalprice.setText(
                getPrice()
        );
    }

    private String getPrice() {
        LocalDateTime startTime = CurrentData.enteredData.getRentalStartDateTime();
        LocalDateTime endTime = CurrentData.enteredData.getRentalEndDateTime();

        Duration duration = Duration.between(startTime, endTime);

        return String.valueOf((duration.toDays() + 1) *
                CurrentData.enteredData.getCarBrand().getPricePerDay());
    }

    @FXML
    protected void onConfirmPriceButtonClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserData.fxml"));

        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(newScene);
    }

    @FXML
    protected void onBackButtonClicked(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("carcharacteristics.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(newScene);

    }
}
