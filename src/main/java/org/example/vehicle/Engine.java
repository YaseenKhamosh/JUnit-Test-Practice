package org.example.vehicle;

public class Engine {

    private boolean running;
    private int horsepower;

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        if (horsepower < 0) {
            throw new IllegalArgumentException("Horsepower cannot be negative.");
        }
        this.horsepower = horsepower;
    }

    public boolean isRunning() {
        return running;
    }

    public void start() {
        if (running) {
         throw new IllegalStateException("Engine is already running.");
        }
        this.running = true;
        System.out.println("Engine started.");
    }

    public void stop() {
        if (!running) {
        throw new IllegalStateException("Engine is not running.");
        }
        this.running = false;
        System.out.println("Engine stopped.");
    }

}
