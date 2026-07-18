package org.example.vehicle;

public class Battery {

    private double batteryLevel;

    public double getChargeLevel() {
        return batteryLevel;
    }

    public void charge(double kWh) {
        if( kWh <= 0) {
            throw new IllegalArgumentException("Charge amount must be positive.");
        }
        batteryLevel += kWh;
        System.out.println("Battery charged by " + kWh + " kWh. Current charge level: " + batteryLevel + " kWh.");
    }

    public void drain(double kWh) {
        if (kWh <= 0) {
            throw new IllegalArgumentException("Drain amount must be positive.");
        }
        if (batteryLevel - kWh < 0) {
            throw new IllegalArgumentException("Cannot drain battery: not enough charge available.");
        }
        batteryLevel -= kWh;
        System.out.println("Battery drained by " + kWh + " kWh. Current charge level: " + batteryLevel + " kWh.");
    }
}
