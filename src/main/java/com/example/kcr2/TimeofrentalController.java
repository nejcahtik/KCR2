package com.example.kcr2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.LocalTimeStringConverter;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeofrentalController {
    public Button skipButton;
    @FXML
    private DatePicker datePickerStart;
    @FXML
    private DatePicker datePickerEnd;
    @FXML
    private ComboBox<String> dropoffhour;
    @FXML
    private ComboBox<String> dropoffminute;
    @FXML
    private ComboBox<String> pickuphour;
    @FXML
    private ComboBox<String> pickupminute;
    @FXML
    private Text errornumberstart;
    @FXML
    private Text errornumberend;
    @FXML
    private Rectangle rect;


    @FXML
    private void initialize() {

        datePickerStart.setValue(LocalDate.now());
        datePickerEnd.setValue(LocalDate.now());
        hideErrors();
        //removeData();


        if(CurrentData.enteredData.getRentalStartDateTime() != null)  {
            datePickerStart.setValue(CurrentData.enteredData.getRentalStartDateTime().toLocalDate());
            datePickerEnd.setValue(CurrentData.enteredData.getRentalEndDateTime().toLocalDate());
            pickuphour.setValue(String.valueOf(CurrentData.enteredData.getRentalStartDateTime().toLocalTime().getHour()));
            pickupminute.setValue(String.valueOf(CurrentData.enteredData.getRentalStartDateTime().toLocalTime().getMinute()));

            dropoffhour.setValue(String.valueOf(CurrentData.enteredData.getRentalEndDateTime().toLocalTime().getHour()));
            dropoffminute.setValue(String.valueOf(CurrentData.enteredData.getRentalEndDateTime().toLocalTime().getMinute()));


        }
    }

    private void removeData() {
        CurrentData.enteredData.setRentalStartDateTime(null);
        CurrentData.enteredData.setRentalEndDateTime(null);
    }

    @FXML
    protected void onConfirmDatesButtonClick(ActionEvent event) throws IOException {
        hideErrors();
        Button button = (Button) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("location.fxml"));

        try {
            Integer.parseInt(pickuphour.getValue());
            Integer.parseInt(pickupminute.getValue());
        }
        catch(Exception e) {
            errornumberstart.setVisible(true);return;
        }

        try {
            Integer.parseInt(dropoffhour.getValue());
            Integer.parseInt(dropoffminute.getValue());
        }
        catch(Exception e) {
            errornumberend.setText("Napaka: Izberite čas zaključka najema.");
            errornumberend.setVisible(true);return;
        }

        LocalDateTime start = LocalDateTime.of(datePickerStart.getValue(),
                LocalTime.of(Integer.parseInt(pickuphour.getValue()), Integer.parseInt(pickupminute.getValue())));
        LocalDateTime end = LocalDateTime.of(datePickerEnd.getValue(),
                LocalTime.of(Integer.parseInt(dropoffhour.getValue()), Integer.parseInt(dropoffminute.getValue())));
        if(Duration.between(start, end).toMinutes() < 0) {
            errornumberend.setText("Napaka: Čas zaključka najema mora biti večji od časa prevzema");
            errornumberend.setVisible(true);
            return;
        }



        CurrentData.enteredData.setRentalStartDateTime(start);
        CurrentData.enteredData.setRentalEndDateTime(end);

        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(newScene);

    }

    private void hideErrors() {
        errornumberend.setVisible(false);
        errornumberstart.setVisible(false);
    }

    @FXML
    protected void onBackButtonClicked(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("welcomescreen.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(newScene);

    }
}
