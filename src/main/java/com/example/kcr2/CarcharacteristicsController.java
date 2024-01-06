package com.example.kcr2;

import javafx.collections.FXCollections;
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

public class CarcharacteristicsController {


    @FXML
    public ComboBox<String>  carengine;

    @FXML
    public Text chooseenginetypetext;
    @FXML
    public Text  choosetransmissiontext;
    @FXML
    public Text carpetrolonly;
    @FXML
    public Text carmanualonly;
    @FXML
    public ComboBox<String>  cartransmission;
    public Text errorchars;

    @FXML
    private void initialize() {

        hideErrors();

        setCarEngineVisibility();
        setCarTransmissionVissibility();
    }

    private void setCarEngineVisibility() {
        if(CurrentData.enteredData.getCarBrand().isDieselVariant() &&
                CurrentData.enteredData.getCarBrand().isPetrolVariant()) {
            carengine.setVisible(true);
            carpetrolonly.setVisible(false);
            chooseenginetypetext.setVisible(true);
        }
        else if(CurrentData.enteredData.getCarBrand().isDieselVariant()) {
            carengine.setVisible(false);
            carpetrolonly.setText("Ta avto je na voljo samo s pogonom na Diesel");
            CurrentData.enteredData.setDieselEngine(true);
            chooseenginetypetext.setVisible(false);
        }
        else if(CurrentData.enteredData.getCarBrand().isPetrolVariant()) {
            carengine.setVisible(false);
            carpetrolonly.setText("Ta avto je na voljo samo s pogonom na bencin");
            CurrentData.enteredData.setDieselEngine(false);
            chooseenginetypetext.setVisible(false);
        }
    }

    private void setCarTransmissionVissibility() {
        if(CurrentData.enteredData.getCarBrand().isAutomaticTransmissionVariant() &&
                CurrentData.enteredData.getCarBrand().isManualTransmissionVariant()) {
            cartransmission.setVisible(true);
            carmanualonly.setVisible(false);
            choosetransmissiontext.setVisible(true);
        }
        else if(CurrentData.enteredData.getCarBrand().isManualTransmissionVariant()) {
            cartransmission.setVisible(false);
            carmanualonly.setText("Ta avto je na voljo samo z ročnim menjalnikom");
            choosetransmissiontext.setVisible(false);
        }
        else if(CurrentData.enteredData.getCarBrand().isAutomaticTransmissionVariant()) {
            cartransmission.setVisible(false);
            carmanualonly.setText("Ta avto je na voljo samo z avtomatskim menjalnikom");
            choosetransmissiontext.setVisible(false);
        }
    }


    @FXML
    protected void onConfirmCarCharacteristicsButtonClicked(ActionEvent event) throws IOException {

        hideErrors();
        Button button = (Button) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("price.fxml"));

        if(CurrentData.enteredData.getCarBrand().isPetrolVariant() && CurrentData.enteredData.getCarBrand().isDieselVariant() &&
            carengine.getValue() == null) {errorchars.setVisible(true);return;}
        if(CurrentData.enteredData.getCarBrand().isManualTransmissionVariant() && CurrentData.enteredData.getCarBrand().isAutomaticTransmissionVariant() &&
                cartransmission.getValue() == null) {errorchars.setVisible(true);return;}

        if(carengine.getValue() != null) {CurrentData.enteredData.setDieselEngine(carengine.getValue().equals("Diesel"));}
        if(cartransmission.getValue() != null) {CurrentData.enteredData.setManualTransmission(cartransmission.getValue().equals("Ročni"));}

        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(newScene);
    }

    private void hideErrors() {
        errorchars.setVisible(false);
    }
}
