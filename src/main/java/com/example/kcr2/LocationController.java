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

public class LocationController {

    public Text errorlocation;
    @FXML
    private ComboBox<String> pickuplocation;
    @FXML
    private ComboBox<String> dropofflocation;
    @FXML
    private Rectangle rect;

    @FXML
    private void initialize() {
        hideErrors();
//        removeData();

        if(!CurrentData.enteredData.getDropoffLocation().equals("")) {
            pickuplocation.setValue(CurrentData.enteredData.getPickupLocation());
            dropofflocation.setValue(CurrentData.enteredData.getDropoffLocation());
        }
    }

    private void removeData() {
        CurrentData.enteredData.setPickupLocation(null);
        CurrentData.enteredData.setDropoffLocation(null);
    }


    @FXML
    protected void onConfirmLocationsButtonClick(ActionEvent event) throws IOException {
        hideErrors();

        if(pickuplocation.getValue() == null ||
            dropofflocation.getValue() == null) {errorlocation.setVisible(true);return;}

        Button button = (Button) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cartype.fxml"));

        CurrentData.enteredData.setPickupLocation(pickuplocation.getValue());
        CurrentData.enteredData.setDropoffLocation(dropofflocation.getValue());

        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(newScene);

    }

    private void hideErrors() {
        errorlocation.setVisible(false);
    }

    @FXML
    protected void onBackButtonClicked(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("timeofrental.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(newScene);

    }
}
