package org.example.Vehicle;

import org.example.vehicle.FuelTank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("FuelTank Test")
public class FuelTankTest {

    FuelTank fuelTank;
    @BeforeEach
    void setUp() {
        fuelTank = new FuelTank();
    }

    @Test
    @DisplayName("Test FuelTank capacity")
    void testFuelTankCapacity() {
        fuelTank.setFuelCapacity(50);
        assertEquals(50, fuelTank.getFuelCapacity(), "Fuel tank capacity should be 50 liters");
    }

    @Test
    @DisplayName("Test FuelTank add fuel")
    void testFuelTankAddFuel() {
        fuelTank.setFuelCapacity(50);
        fuelTank.addFuel(20);
        assertEquals(20, fuelTank.getCurrentFuelLevel(), "Fuel tank should have 20 liters of fuel");
    }

    @Test
    @DisplayName("Test FuelTank consume fuel")
    void testFuelTankConsumeFuel() {
        fuelTank.setFuelCapacity(50);
        fuelTank.addFuel(30);
        fuelTank.consumeFuel(10);
        assertEquals(20, fuelTank.getCurrentFuelLevel(), "Fuel tank should have 20 liters of fuel after consuming 10 liters");
    }

    @Test
    @DisplayName("Test FuelTank consume fuel more than available")
    void testFuelTankConsumeFuelMoreThanAvailable() {
        fuelTank.setFuelCapacity(50);
        fuelTank.addFuel(10);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> fuelTank.consumeFuel(20), () ->"Should throw exception when consuming more fuel than available");
        assertEquals("Cannot consume fuel: not enough fuel available.", exception.getMessage(), "Should throw exception when consuming more fuel than available");
    }

    @Test
    @DisplayName("Test FuelTank add fuel more than capacity")
    void testFuelTankAddFuelMoreThanCapacity() {
        fuelTank.setFuelCapacity(50);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> fuelTank.addFuel(60), () -> "Should throw exception when adding more fuel than capacity");
        assertEquals("Cannot add fuel: exceeds maximum capacity.", exception.getMessage(), "Should throw exception when adding more fuel than capacity");
    }


    @Test
    @DisplayName("Test FuelTank set negative capacity")
    void testFuelTankSetNegativeCapacity() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> fuelTank.setFuelCapacity(-10), () -> "Should throw exception when setting negative fuel capacity");
        assertEquals("Fuel must be positive", exception.getMessage(), "Should throw exception when setting negative fuel capacity");
    }

    @Test
    @DisplayName("Test FuelTank add negative fuel")
    void testFuelTankAddNegativeFuel() {
        fuelTank.setFuelCapacity(50);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> fuelTank.addFuel(-5), () -> "Should throw exception when adding negative fuel");
        assertEquals("Liters must be positive", exception.getMessage(), "Should throw exception when adding negative fuel");
    }


    @Test
    @DisplayName("Test FuelTank consume negative fuel")
    void testFuelTankConsumeNegativeFuel() {
        fuelTank.setFuelCapacity(50);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> fuelTank.consumeFuel(-5));
        assertEquals("Liters must be positive", exception.getMessage(), "Should throw exception when consuming negative fuel");
    }


    @Test
    @DisplayName("Test FuelTank Consumes more fuel than available")
    void testFuelTankConsumeMoreFuelThanAvailable() {
        fuelTank.setFuelCapacity(50);
        fuelTank.addFuel(10);
        IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> fuelTank.consumeFuel(15));
        assertEquals("Cannot consume fuel: not enough fuel available.", exception.getMessage(), "Should throw exception when consuming more fuel than available");
    }

    @Test
    @DisplayName("Test FuelTank fill exactly to capacity (boundary)")
    void testFuelTankFillToExactCapacity() {
        fuelTank.setFuelCapacity(50);
        fuelTank.addFuel(50);
        assertEquals(50, fuelTank.getCurrentFuelLevel(), "Filling exactly to capacity should be allowed");
    }

    @Test
    @DisplayName("Test FuelTank consume all fuel to zero (boundary)")
    void testFuelTankConsumeAllToZero() {
        fuelTank.setFuelCapacity(50);
        fuelTank.addFuel(30);
        fuelTank.consumeFuel(30);
        assertEquals(0, fuelTank.getCurrentFuelLevel(), "Consuming all fuel should leave exactly 0 liters");
    }

    @Test
    @DisplayName("Test FuelTank capacity defaults to zero before it is set")
    void testFuelTankCapacityDefaultsToZero() {
        assertEquals(0, fuelTank.getFuelCapacity(), "A fresh fuel tank should report 0 capacity until set");
    }

    @Test
    @DisplayName("Test FuelTank add fuel before capacity is set fails")
    void testFuelTankAddFuelBeforeCapacitySet() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> fuelTank.addFuel(10));
        assertEquals("Cannot add fuel: exceeds maximum capacity.", exception.getMessage(), "Adding fuel before setting capacity should fail because capacity defaults to 0");
    }
}
