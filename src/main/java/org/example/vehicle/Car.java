package org.example.vehicle;

import org.example.enums.Color;
import org.example.enums.FuelType;
import org.example.enums.TransmissionType;

public abstract class Car extends Vehicle {

    private final String make;
    private final String model;
    private final String licensePlate;

    private Engine engine;
    private FuelTank fuelTank;
    private Battery battery;


    protected Car(
            int year,
            Color color,
            double price,
            String make,
            String model,
            String licensePlate,
            Engine engine,
            FuelTank fuelTank,
            Battery battery
    ) {
        super(color, year, price);
        this.make = make;
        this.model = model;
        this.licensePlate = licensePlate;
        this.engine = engine;
        this.fuelTank = fuelTank;
        this.battery = battery;
    }


    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }
    public String getLicensePlate() {
        return licensePlate;
    }

    public Engine getEngine() {
        return engine;
    }
    public FuelTank getFuelTank() {
        return fuelTank;
    }

    public Battery getBattery() {
        return battery;
    }
}
