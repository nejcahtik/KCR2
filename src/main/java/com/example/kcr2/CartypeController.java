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

public class CartypeController {

    public Text errortype;
    @FXML
    private ComboBox<String> cartype;
    @FXML
    private Rectangle rect;

    @FXML
    private void initialize() {
        hideErrors();
//        removeData();

        if(CurrentData.enteredData.getCarType() != null) {
            cartype.setValue(getCarTypeFromValue());
        }

    }

    private String getCarTypeFromValue() {
        String carType = CurrentData.enteredData.getCarType().name();

        if(carType.equals("SMALL")) {
            return "Majhen";
        }
        else if(carType.equals("MEDIUM")) {
            return "Srednji";
        }
        else if(carType.equals("LARGE")) {
            return "Velik";
        }
        return "";
    }

    private void removeData() {
        CurrentData.enteredData.setCarType(null);
    }

    @FXML
    protected void onConfirmCarTypeButtonClick(ActionEvent event) throws IOException {
        hideErrors();
        Button button = (Button) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("carbrand.fxml"));

        if(cartype.getValue() == null) {errortype.setVisible(true);return;}

        CurrentData.enteredData.setCarType(getCarTypeFromString(cartype.getValue()));

        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(newScene);

    }

    private void hideErrors() {
        errortype.setVisible(false);
    }

    private CarType getCarTypeFromString(String carTypeString) {
        switch(carTypeString) {
            case "Majhen":
                return CarType.SMALL;
            case "Srednji":
                return CarType.MEDIUM;
            case "Velik":
                return CarType.LARGE;
            default:
                throw new IllegalArgumentException("this car type not supported");
        }
    }

    @FXML
    protected void onBackButtonClicked(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("location.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(newScene);

    }


}
