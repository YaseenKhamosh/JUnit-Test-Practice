package org.example.vehicle;

import org.example.enums.Color;
import org.example.interfaces.Rechargeable;
import org.example.interfaces.Refuelable;

public class Prius extends Car implements Rechargeable, Refuelable {


    public Prius(
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
        super(year, color, price, make, model, licensePlate, engine, fuelTank, battery);
    }
    public void drive() {
        System.out.println("Prius is driving.");
    }

    public void park() {
        System.out.println("Prius is parked.");
    }

    @Override
    public void recharge(double kWh) {
        getBattery().charge(kWh);
    }

    @Override
    public void refuel(double liters) {
        getFuelTank().addFuel(liters);
    }
}
