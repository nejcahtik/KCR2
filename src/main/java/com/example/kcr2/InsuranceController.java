package com.example.kcr2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class InsuranceController {
    public CheckBox insurancecheckbox;
    @FXML
    private Text insuranceprice;

    @FXML
    private void initialize() {
        insuranceprice.setText(String.valueOf(CurrentData.enteredData.getCarBrand().getInsurancePricePerDay()));
    }

    @FXML
    protected void onConfirmInsuranceButtonClick(ActionEvent event) throws IOException {
        if (insurancecheckbox.isSelected()) {
            CurrentData.enteredData.setInsuranceChosen(true);
        }

        Button button = (Button) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("paymenttype.fxml"));

        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(newScene);
    }
}
