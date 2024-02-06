package com.example.kcr2;


import java.time.LocalDateTime;

public class EnteredData {
    public LocalDateTime getRentalStartDateTime() {
        return rentalStartDateTime;
    }

    public LocalDateTime getRentalEndDateTime() {
        return rentalEndDateTime;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public String getDropoffLocation() {
        return dropoffLocation;
    }

    public CarType getCarType() {
        return carType;
    }

    public CarBrand getCarBrand() {return carBrand;}

    public boolean isManualTransmission() {
        return manualTransmission;
    }

    public boolean isDieselEngine() {
        return dieselEngine;
    }

    public UserData getUserData() {
        return userData;
    }

    public boolean isInsuranceChosen() {
        return insuranceChosen;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public CreditCardData getCreditCardData() {
        return creditCardData;
    }

    private LocalDateTime rentalStartDateTime;
    private LocalDateTime rentalEndDateTime;

    public void setRentalStartDateTime(LocalDateTime rentalStartDateTime) {
        this.rentalStartDateTime = rentalStartDateTime;
    }

    public void setRentalEndDateTime(LocalDateTime rentalEndDateTime) {
        this.rentalEndDateTime = rentalEndDateTime;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public void setDropoffLocation(String dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public void setManualTransmission(boolean manualTransmission) {
        this.manualTransmission = manualTransmission;
    }

    public void setDieselEngine(boolean dieselEngine) {
        this.dieselEngine = dieselEngine;
    }


    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public void setInsuranceChosen(boolean insuranceChosen) {
        this.insuranceChosen = insuranceChosen;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public void setCreditCardData(CreditCardData creditCardData) {
        this.creditCardData = creditCardData;
    }
    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    private String pickupLocation = "";
    private String dropoffLocation = "";

    private CarType carType;

    private CarBrand carBrand;

    private boolean manualTransmission;
    private boolean dieselEngine;
    private UserData userData = new UserData();
    public boolean userDataSet = false;
    private boolean insuranceChosen;
    private PaymentType paymentType;
    private CreditCardData creditCardData = new CreditCardData();
}

enum CarType {
    SMALL, MEDIUM, LARGE
}

class CarBrand {

    public String getName() {
        return name;
    }

    public boolean isDieselVariant() {
        return dieselVariant;
    }

    public boolean isPetrolVariant() {
        return petrolVariant;
    }

    public boolean isManualTransmissionVariant() {
        return manualTransmissionVariant;
    }

    public boolean isAutomaticTransmissionVariant() {
        return automaticTransmissionVariant;
    }

    public long getPricePerDay() {
        return pricePerDay;
    }

    public long getInsurancePricePerDay() {
        return insurancePricePerDay;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setPricePerDay(long pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void setInsurancePricePerDay(long insurancePricePerDay) {
        this.insurancePricePerDay = insurancePricePerDay;
    }

    public void setDieselVariant(boolean dieselVariant) {
        this.dieselVariant = dieselVariant;
    }

    public void setPetrolVariant(boolean petrolVariant) {
        this.petrolVariant = petrolVariant;
    }

    public void setManualTransmissionVariant(boolean manualTransmissionVariant) {
        this.manualTransmissionVariant = manualTransmissionVariant;
    }

    public void setAutomaticTransmissionVariant(boolean automaticTransmissionVariant) {
        this.automaticTransmissionVariant = automaticTransmissionVariant;
    }


    private String name;
    private boolean dieselVariant;
    private boolean petrolVariant;
    private boolean manualTransmissionVariant;
    private boolean automaticTransmissionVariant;
    private long pricePerDay;
    private long insurancePricePerDay;

    public CarBrand(String name, boolean dieselVariant, boolean petrolVariant, boolean manualTransmissionVariant,
                    boolean automaticTransmissionVariant,
                    long pricePerDay, long insurancePricePerDay) {
        this.name = name; this.dieselVariant = dieselVariant; this.petrolVariant = petrolVariant;
        this.manualTransmissionVariant = manualTransmissionVariant; this.automaticTransmissionVariant = automaticTransmissionVariant;
        this.pricePerDay = pricePerDay; this.insurancePricePerDay = insurancePricePerDay;
    }
}

enum PaymentType {
    CREDIT_CARD, CASH
}

class CreditCardData {
    public String getThatLongNumberOnTheCard() {
        return thatLongNumberOnTheCard;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getCCV() {
        return CCV;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setThatLongNumberOnTheCard(String thatLongNumberOnTheCard) {
        this.thatLongNumberOnTheCard = thatLongNumberOnTheCard;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public void setCCV(String CCV) {
        this.CCV = CCV;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    private String thatLongNumberOnTheCard;
    private String cardHolder;
    private String CCV;
    private LocalDateTime expiryDate;
}

class UserData {
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public String getYearsOfDriving() {
        return yearsOfDriving;
    }

    private String name;
    private String surname;
    private String address;

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBirthday(LocalDateTime age) {
        this.birthday = age;
    }

    public void setYearsOfDriving(String yearsOfDriving) {
        this.yearsOfDriving = yearsOfDriving;
    }

    private String email;
    private String phoneNumber;
    private LocalDateTime birthday;
    private String yearsOfDriving;
}

class CurrentData {
    public final static EnteredData enteredData = new EnteredData();
    public final static AvailableCars availableCars = new AvailableCars();
}
