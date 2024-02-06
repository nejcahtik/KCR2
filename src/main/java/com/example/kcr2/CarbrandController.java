package com.example.kcr2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.time.LocalDate;
import java.util.stream.Collectors;

public class CarbrandController {

    public Text errorbrand;
    @FXML
    private ComboBox<String> carbrand;
    @FXML
    private Rectangle rect;

    @FXML
    private void initialize() {

        initCarBrands();
        hideErrors();
//        removeData();
    }

    private void removeData() {
        CurrentData.enteredData.setCarBrand(null);
    }

    @FXML
    protected void onConfirmCarBrandButtonClick(ActionEvent event) throws IOException {
        hideErrors();
        Button button = (Button) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("carcharacteristics.fxml"));

        if(carbrand.getValue() == null) {errorbrand.setVisible(true);return;}

        CurrentData.enteredData.setCarBrand(getCarBrandFromString(carbrand.getValue()));

        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(newScene);

    }

    private void hideErrors() {
        errorbrand.setVisible(false);
    }


    private void initCarBrands() {

        switch(CurrentData.enteredData.getCarType()) {
            case SMALL:
                carbrand.setItems(getSmallCars());
                break;
            case MEDIUM:
                carbrand.setItems(getMediumCars());
                break;
            case LARGE:
                carbrand.setItems(getLargeCars());
                break;

        }

        if(CurrentData.enteredData.getCarBrand() != null) {
            String name = CurrentData.enteredData.getCarBrand().getName();
            carbrand.setValue(name);
        }
    }

    private ObservableList<String> getSmallCars() {
        return CurrentData.availableCars.getAvailableSmallCars()
                        .stream()
                        .map(CarBrand::getName)
                        .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    private ObservableList<String> getMediumCars() {
        return CurrentData.availableCars.getAvailableMediumCars()
                .stream()
                .map(CarBrand::getName)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    private ObservableList<String> getLargeCars() {
        return CurrentData.availableCars.getAvailableBigCars()
                .stream()
                .map(CarBrand::getName)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    private CarBrand getCarBrandFromString(String carBrandString) {
        switch(CurrentData.enteredData.getCarType()) {
            case SMALL:
                return findSmallCar(carBrandString);
            case MEDIUM:
                return findMediumCar(carBrandString);
            case LARGE:
                return findLargeCar(carBrandString);
        }

        return null;
    }

    private CarBrand findSmallCar(String carBrandString) {
        return CurrentData.availableCars.getAvailableSmallCars().stream()
                .filter(c -> c.getName().equals(carBrandString))
                .findAny()
                .orElseGet(null);
    }
    private CarBrand findMediumCar(String carBrandString) {
        return CurrentData.availableCars.getAvailableMediumCars().stream()
                .filter(c -> c.getName().equals(carBrandString))
                .findAny()
                .orElseGet(null);
    }
    private CarBrand findLargeCar(String carBrandString) {
        return CurrentData.availableCars.getAvailableBigCars().stream()
                .filter(c -> c.getName().equals(carBrandString))
                .findAny()
                .orElseGet(null);
    }

    @FXML
    protected void onBackButtonClicked(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cartype.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(newScene);

    }

}
