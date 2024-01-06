package com.example.kcr2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;


import java.io.IOException;
import java.time.LocalTime;

public class CreditcarddetailsController {
    public TextField name;
    public TextField cardnumber;
    public TextField ccv;
    public TextField year;
    public TextField month;


    public Text errornumber;
    public Text errorname;
    public Text errorcardnumber;

    @FXML
    private void initialize() {

        hideErrors();
    }



    @FXML
    protected void onConfirmPayment(ActionEvent event) throws IOException {
        hideErrors();

        if(name.getText().equals("") || cardnumber.getText().equals("") ||
            ccv.getText().equals("")) {errorname.setVisible(true);return;}

        try {
            long cn = Long.parseLong(cardnumber.getText());
            long c = Long.parseLong(ccv.getText());

            if(!(countDigits(cn) == 16)) {
                errorcardnumber.setText("Napaka: Številka kartice ni veljavna");
                errorcardnumber.setVisible(true);
                return;
            }

            if(!(countDigits(c) == 3)) {
                errorcardnumber.setText("Napaka: Številka CCV ni veljavna");
                errorcardnumber.setVisible(true);
                return;
            }

        } catch(Exception e) {
            errorcardnumber.setText("Napaka: Številka kartice in CCV morata biti številki");
            errorcardnumber.setVisible(true);
            return;
        }

        try {
            int y = Integer.parseInt(year.getText());
            int m = Integer.parseInt(month.getText());

            if(isCardExpired(y, m)) {
                errornumber.setText("Napaka: Kartica je pretečena");
                errornumber.setVisible(true);
                return;
            }
        }
        catch(Exception e) {
            errornumber.setText("Napaka: Vnesite veljavno leto in veljaven mesec");
            errornumber.setVisible(true);
            return;
        }

        setEnteredData();

        Button button = (Button) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("orderdetails.fxml"));

        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(newScene);
    }

    private void setEnteredData() {
        CurrentData.enteredData.getCreditCardData().setCardHolder(name.getText());
        CurrentData.enteredData.getCreditCardData().setCCV(ccv.getText());
        CurrentData.enteredData.getCreditCardData().setThatLongNumberOnTheCard(cardnumber.getText());
        CurrentData.enteredData.getCreditCardData().setExpiryDate(LocalDateTime.of(LocalDate.of(Integer.parseInt(year.getText()),
                Integer.parseInt(month.getText()),1), LocalTime.of(0,0,0)));
    }

    private void hideErrors() {
        errornumber.setVisible(false);
        errorname.setVisible(false);
        errorcardnumber.setVisible(false);
    }

    private boolean isCardExpired(int y, int m) {
        LocalDateTime cardExpiry = LocalDateTime.of(LocalDate.of(y,m,1), LocalTime.of(0,0,0));

        return Duration.between(cardExpiry, LocalDateTime.now()).toHours() >= 0;
    }

    private static int countDigits(long number) {
        String numberAsString = Long.toString(Math.abs(number));
        return numberAsString.length();
    }
}