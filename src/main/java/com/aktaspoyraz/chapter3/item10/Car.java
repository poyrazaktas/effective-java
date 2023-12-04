package com.aktaspoyraz.chapter3.item10;

import java.util.Objects;

public class Car {
    private final String brand;
    private final String model;
    private final int year;

    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    @Override
    public boolean equals(Object obj) {
        // 1. check if the argument is a reference to this object
        if (obj == this) {
            return true;
        }
        // 2. use the instanceof operator to check if the argument has the correct type
        // p.s whenever you check the arguments type, you provide the null check too.
        // since null is not instance of your type
        if (!(obj instanceof Car)) {
            return false;
        }
        // 3. cast the argument to the correct type
        Car car = (Car) obj;
        // 4. for each "significant" field in the class, check if that field of the argument
        // matches the corresponding field of this object.
        // to prevent possible null pointer exceptions use Objects.equals
        return Objects.equals(this.brand, car.brand) && Objects.equals(this.model, car.model)
                && this.year == car.year;
    }
}
