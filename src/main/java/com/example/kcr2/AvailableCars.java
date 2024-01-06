package com.example.kcr2;

import java.util.ArrayList;

public class AvailableCars {

    public ArrayList<CarBrand> getAvailableSmallCars() {
        return availableSmallCars;
    }

    public ArrayList<CarBrand> getAvailableMediumCars() {
        return availableMediumCars;
    }

    public ArrayList<CarBrand> getAvailableBigCars() {
        return availableBigCars;
    }

    public ArrayList<CarBrand> availableSmallCars = new ArrayList<>();
    public ArrayList<CarBrand> availableMediumCars = new ArrayList<>();
    public ArrayList<CarBrand> availableBigCars = new ArrayList<>();


    public AvailableCars() {

        availableSmallCars.add(new CarBrand("Audi A3", true, true, false, true, 90, 3));
        availableSmallCars.add(new CarBrand("Toyota Yaris", false, true, true, true, 60, 1));
        availableSmallCars.add(new CarBrand("Nissan Micra", true, true, false, true, 50, 1));


        availableMediumCars.add(new CarBrand("MB C180", true, false, true, true, 110, 4));
        availableMediumCars.add(new CarBrand("Audi A5", true, false, false, true, 100, 3));
        availableMediumCars.add(new CarBrand("Toyota Avensis", false, true, true, true, 60, 2));
        availableMediumCars.add(new CarBrand("Honda Accord", true, false, true, true, 65, 2));
        availableMediumCars.add(new CarBrand("Skoda Octavia", true, true, true, true, 70, 2));

        availableBigCars.add(new CarBrand("Citroen Berlingo", true, false, false, true, 75, 2));
        availableBigCars.add(new CarBrand("Citroen C4 Grand Picasso", true, false, false, true, 70, 1));
        availableBigCars.add(new CarBrand("Ford C-MAX", true, true, true, true, 70, 2));

    }

}
