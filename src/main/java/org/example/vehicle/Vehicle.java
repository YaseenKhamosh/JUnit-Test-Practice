package org.example.vehicle;

import org.example.enums.Color;

public abstract class Vehicle {
    private final Color color;
    private final int year;
    private double currentSpeed;
    private double price;

    protected Vehicle( Color color, int year, double price) {
        this.color = color;
        this.year = year;
        this.price = price;
        this.currentSpeed = 0;
    }

    public Color getColor() {
        return color;
    }
    public int getYear() {
        return year;
    }
    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = price;
    }

    public void accelerate(double delta) {
        if (delta < 0) {
            throw new IllegalArgumentException("Acceleration must be positive.");
        }
        currentSpeed += delta;
    }

    public void brake(double delta) {
        if (delta < 0) {
            throw new IllegalArgumentException("Braking must be positive.");
        }
        currentSpeed = Math.max(0, currentSpeed - delta);
    }
}
