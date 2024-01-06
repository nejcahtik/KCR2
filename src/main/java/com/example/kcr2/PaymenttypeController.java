package com.example.kcr2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

public class PaymenttypeController {

    @FXML
    private Text finalprice;

    @FXML
    private void initialize() {

        finalprice.setText(getFinalPrice());
    }

    private String getFinalPrice() {
        if(CurrentData.enteredData.isInsuranceChosen()) {
            return String.valueOf(
                    getPriceWithoutInsurance() + getInsurancePrice()
            );
        }

        return String.valueOf(getPriceWithoutInsurance());
    }

    private long getPriceWithoutInsurance() {
        LocalDateTime startDate = CurrentData.enteredData.getRentalStartDateTime();
        LocalDateTime endDate = CurrentData.enteredData.getRentalEndDateTime();

        Duration duration = Duration.between(startDate, endDate);
        return (duration.toDays()+1) *
                CurrentData.enteredData.getCarBrand().getPricePerDay();
    }

    private long getInsurancePrice() {
        LocalDateTime startDate = CurrentData.enteredData.getRentalStartDateTime();
        LocalDateTime endDate = CurrentData.enteredData.getRentalEndDateTime();

        Duration duration = Duration.between(startDate, endDate);
        return (duration.toDays() + 1) *
                CurrentData.enteredData.getCarBrand().getInsurancePricePerDay();
    }

    @FXML
    protected void onPaymentTypeCash(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("orderdetails.fxml"));

        CurrentData.enteredData.setPaymentType(PaymentType.CASH);

        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(newScene);
    }

    @FXML
    protected void onPaymentTypeCard(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("creditcarddetails.fxml"));

        CurrentData.enteredData.setPaymentType(PaymentType.CREDIT_CARD);

        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(newScene);
    }
}
