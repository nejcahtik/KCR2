package com.example.kcr2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.*;

public class UserdataController {

    public TextField email;
    public TextField phone;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField address;
    @FXML
    private DatePicker birthdate;
    @FXML
    private TextField yearsoflicense;
    @FXML
    private Text erroraddress;
    @FXML
    private Text errorolderthan21;
    @FXML
    private Text errorlicense;
    @FXML
    private Text errorphone;

    @FXML
    private void initialize() {
        birthdate.setValue(LocalDate.of(1980, 1, 1));
        removeErrors();
    }



    @FXML
    protected void onConfirmUserDataButtonClick(ActionEvent event) throws IOException {
        removeErrors();
        if(!name.getText().equals("")) {CurrentData.enteredData.getUserData().setName(name.getText());}
        else {
            erroraddress.setText("Napaka: Polje 'ime' je obvezno");
            erroraddress.setVisible(true);return;
        }

        if(!surname.getText().equals("")){CurrentData.enteredData.getUserData().setSurname(surname.getText());}
        else {
            erroraddress.setText("Napaka: Polje 'priimek' je obvezno");
            erroraddress.setVisible(true);return;
        }

        if(!address.getText().equals("")) {CurrentData.enteredData.getUserData().setAddress(address.getText());}
        else {
            erroraddress.setText("Napaka: Polje 'naslov' je obvezno");
            erroraddress.setVisible(true);return;
        }

        if(!address.getText().equals("")) {CurrentData.enteredData.getUserData().setAddress(address.getText());}
        else {
            erroraddress.setVisible(true);return;
        }

        if(!phone.getText().equals("") || !email.getText().equals("")) {
            CurrentData.enteredData.getUserData().setEmail(email.getText());
            CurrentData.enteredData.getUserData().setPhoneNumber(phone.getText());
        }
        else {errorphone.setVisible(true);return;}

        if(birthdate.getValue() == null) {
            errorolderthan21.setVisible(true);
            errorolderthan21.setText("Napaka: izberite datum rojstva");
            return;
        }

        if(isPersonOlderThan21(birthdate.getValue())) {CurrentData.enteredData.getUserData().setBirthday(LocalDateTime.of(birthdate.getValue(), LocalTime.of(0,0,0)));}
        else {errorolderthan21.setVisible(true);
            errorolderthan21.setText("Napaka: avto lahko najamejo samo osebe, starejše od 21 let.");
            return;
        }

        try {
            int yearsOfLicense = Integer.parseInt(yearsoflicense.getText());

            if(yearsOfLicense < 2) {
                errorlicense.setText("Napaka: avtomobil lahko najamejo le osebe, ki so vozniško dovoljenje pridobili pred dvema letoma ali več");
                errorlicense.setVisible(true);return;
            }
        }
        catch(Exception e) {
            errorlicense.setText("Napaka: vnesite število let (celo število)");
            errorlicense.setVisible(true);return;
        }

        Button button = (Button) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("insurance.fxml"));

        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(newScene);
    }

    private boolean isPersonOlderThan21(LocalDate birthday) {
        LocalDateTime bddatetime = LocalDateTime.of(birthday, LocalTime.of(0,0,0));

        Period duration = Period.between(bddatetime.toLocalDate(), LocalDateTime.now().toLocalDate());

        return duration.getYears() >= 21;
    }

    private void removeErrors() {
        erroraddress.setVisible(false);
        errorolderthan21.setVisible(false);
        errorlicense.setVisible(false);
        errorphone.setVisible(false);
    }
}