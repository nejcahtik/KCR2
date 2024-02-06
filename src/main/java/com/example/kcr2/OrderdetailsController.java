package com.example.kcr2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

public class OrderdetailsController {
    public Text pickupdate;
    public Text dropoffdate;
    public Text dropofflocation;
    public Text pickuplocation;
    public Text carbrand;
    public Text transmission;
    public Text engine;
    public Text finalprice;
    public Text insurance;
    public Text name;
    public Text address;
    public Text paymenttypecard;
    public Text gmail;
    public Text phone;
    public Text carddetails;
    @FXML
    private Rectangle rect;

    @FXML
    private void initialize() {
        pickupdate.setText("Ob " + CurrentData.enteredData.getRentalStartDateTime().getHour() + ":" +
                CurrentData.enteredData.getRentalStartDateTime().getMinute() + "   Dne " +
                CurrentData.enteredData.getRentalStartDateTime().getDayOfMonth() + ". " +
                CurrentData.enteredData.getRentalStartDateTime().getMonthValue() + ". " +
                CurrentData.enteredData.getRentalStartDateTime().getYear());

        dropoffdate.setText("Ob " + CurrentData.enteredData.getRentalEndDateTime().getHour() + ":" +
                CurrentData.enteredData.getRentalEndDateTime().getMinute() + "   Dne " +
                CurrentData.enteredData.getRentalEndDateTime().getDayOfMonth() + ". " +
                CurrentData.enteredData.getRentalEndDateTime().getMonthValue() + ". " +
                CurrentData.enteredData.getRentalEndDateTime().getYear());

        pickuplocation.setText(CurrentData.enteredData.getPickupLocation());
        dropofflocation.setText(CurrentData.enteredData.getDropoffLocation());

        carbrand.setText(CurrentData.enteredData.getCarBrand().getName());
        transmission.setText(CurrentData.enteredData.isManualTransmission() ? "Ročni" : "Avtomatski");
        engine.setText(CurrentData.enteredData.isDieselEngine() ? "Diesel" : "Bencin");

        finalprice.setText(getFinalPrice());

        insurance.setText(CurrentData.enteredData.isInsuranceChosen() ? "DA" : "NE");

        name.setText(CurrentData.enteredData.getUserData().getName() + " " +
                CurrentData.enteredData.getUserData().getSurname());
        address.setText(CurrentData.enteredData.getUserData().getAddress());
        gmail.setText(CurrentData.enteredData.getUserData().getEmail());
        phone.setText(CurrentData.enteredData.getUserData().getPhoneNumber());

        if (CurrentData.enteredData.getPaymentType() == PaymentType.CASH) {
            paymenttypecard.setText("Plačal bom ob prevzemu");
            carddetails.setVisible(false);
        } else if (CurrentData.enteredData.getPaymentType() == PaymentType.CREDIT_CARD) {
            paymenttypecard.setText("Plačal bom prek spleta");
            carddetails.setVisible(true);
            carddetails.setText(hideCardData(CurrentData.enteredData.getCreditCardData().getThatLongNumberOnTheCard()));
        }
    }

    @FXML
    protected void onConfirmEverything(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("thankyoucomeagain.fxml"));

        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(newScene);
    }

    private static String hideCardData(String cardNumberString) {
        if (cardNumberString.length() >= 12) {
            return "XXXX-XXXX-XXXX-" + cardNumberString.substring(12);
        } else {
            System.out.println("Input string has less than 12 characters.");
            return cardNumberString;
        }
    }
    private String getFinalPrice() {
        if (CurrentData.enteredData.isInsuranceChosen()) {
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
        return (duration.toDays() + 1) *
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
    protected void onBackButtonClicked(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        FXMLLoader loader;
        if(CurrentData.enteredData.getPaymentType() == PaymentType.CASH) {
            loader = new FXMLLoader(getClass().getResource("paymenttype.fxml"));
        }
        else {
            loader = new FXMLLoader(getClass().getResource("creditcarddetails.fxml"));
        }

        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(newScene);

    }

    @FXML
    protected void onDateFix(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        FXMLLoader loader = new FXMLLoader((getClass().getResource("timeofrental.fxml")));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(newScene);
    }

    @FXML
    protected void onLocationFix(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        FXMLLoader loader = new FXMLLoader((getClass().getResource("location.fxml")));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(newScene);
    }

    @FXML
    protected void onDriverFix(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        FXMLLoader loader = new FXMLLoader((getClass().getResource("UserData.fxml")));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(newScene);
    }

    @FXML
    protected void onPaymentFix(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        FXMLLoader loader = new FXMLLoader((getClass().getResource("paymenttype.fxml")));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(newScene);
    }

    @FXML
    protected void onCarFix(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        FXMLLoader loader = new FXMLLoader((getClass().getResource("cartype.fxml")));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(newScene);
    }
}