package org.example.vehicle;

public class FuelTank {
    private double currentFuelLevel;
    private double maxFuelCapacity;

    public void setFuelCapacity(double capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Fuel must be positive");
        }
        this.maxFuelCapacity = capacity;
        System.out.println("Fuel tank capacity is: " + maxFuelCapacity);
    }

    public double getFuelCapacity() {
        System.out.println("Fuel tank capacity is: " + maxFuelCapacity);
        return maxFuelCapacity;
    }

    public double getCurrentFuelLevel() {
        return currentFuelLevel;
    }

    public void addFuel(double liters) {
        if (liters <= 0) {
            throw  new IllegalArgumentException("Liters must be positive");
        }
        if (currentFuelLevel + liters > maxFuelCapacity) {
            throw new IllegalArgumentException("Cannot add fuel: exceeds maximum capacity.");
        }
        currentFuelLevel += liters;
        System.out.println("Added " + liters + " liters of fuel. Current fuel level: " + currentFuelLevel);
    }

    public void consumeFuel(double liters) {
        if (liters <= 0) {
            throw new IllegalArgumentException("Liters must be positive");
        }
        if (currentFuelLevel - liters < 0) {
            throw new IllegalArgumentException("Cannot consume fuel: not enough fuel available.");
        }
        currentFuelLevel -= liters;
        System.out.println("Consumed " + liters + " units of fuel. Current fuel level: " + currentFuelLevel);
    }
}
