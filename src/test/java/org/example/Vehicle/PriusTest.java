package org.example.Vehicle;

import org.example.enums.Color;
import org.example.factory.PriusFactory;
import org.example.vehicle.Prius;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PriusTest {
    Prius prius;
    @BeforeEach
    void setUp() {
         prius = PriusFactory.createPrius();
    }


    @Test
    @DisplayName("Pruis color should be white")
    void testPriusColor() {
        assertEquals(Color.WHITE, prius.getColor(), "Prius color should be white");
    }

    @Test
    @DisplayName("Pruis year should be 2022")
    void testPriusYear() {
        assertEquals(2022, prius.getYear(), "Prius year should be 2022");
    }


    @Test
    @DisplayName("Pruis price should be 25000.0")
    void testPriusPrice() {
        assertEquals(25000.0, prius.getPrice(), "Prius price should be 25000.0");
    }

    @Test
    @DisplayName("Pruis make should be Toyota")
    void testPriusMake() {
        assertEquals("Toyota", prius.getMake(),"Prius make should be Toyota" );
    }

    @Test
    @DisplayName("Pruis model should be Prius")
    void testPriusModel() {
        assertEquals("Prius", prius.getModel(), "Prius model should be Prius");
    }

    @Test
    @DisplayName("Pruis license plate should be ABC123")
    void testPriusLicensePlate() {
        assertEquals("ABC123", prius.getLicensePlate(), "Prius license plate should be ABC123");
    }

    @Test
    @DisplayName("Accelerate increases current speed")
    void testAccelerate() {
        prius.accelerate(30);
        assertEquals(30, prius.getCurrentSpeed(), "Speed should be 30 after accelerating by 30");
    }

    @Test
    @DisplayName("Brake decreases current speed but not below zero")
    void testBrake() {
        prius.accelerate(30);
        prius.brake(50);
        assertEquals(0, prius.getCurrentSpeed(), "Speed should clamp to 0, never go negative");
    }

    @Test
    @DisplayName("Accelerate with negative value throws")
    void testAccelerateNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> prius.accelerate(-5));
        assertEquals("Acceleration must be positive.", exception.getMessage());
    }

    @Test
    @DisplayName("setPrice updates the price")
    void testSetPrice() {
        prius.setPrice(30000.0);
        assertEquals(30000.0, prius.getPrice(), "Price should update to 30000.0");
    }

    @Test
    @DisplayName("setPrice with negative value throws")
    void testSetPriceNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> prius.setPrice(-1));
        assertEquals("Price cannot be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("recharge delegates to the battery")
    void testRecharge() {
        prius.recharge(15);
        assertEquals(15, prius.getBattery().getChargeLevel(), "Battery should hold 15 kWh after recharging");
    }

    @Test
    @DisplayName("refuel delegates to the fuel tank")
    void testRefuel() {
        prius.getFuelTank().setFuelCapacity(50);
        prius.refuel(20);
        assertEquals(20, prius.getFuelTank().getCurrentFuelLevel(), "Fuel tank should hold 20 liters after refueling");
    }

    @Test
    @DisplayName("Accelerate then partial brake nets the difference")
    void testAccelerateThenPartialBrake() {
        prius.accelerate(30);
        prius.brake(10);
        assertEquals(20, prius.getCurrentSpeed(), "Speed should be 20 after accelerating 30 and braking 10");
    }

    @Test
    @DisplayName("Prius is a Vehicle")
    void testPriusIsVehicleAndRechargeable() {
        assertInstanceOf(org.example.vehicle.Vehicle.class, prius, "A Prius should be a Vehicle");
    }

    @Test
    @DisplayName("Prius is rechargeable")
    void testPriusIsRechargeable() {
        assertInstanceOf(org.example.interfaces.Rechargeable.class, prius, "A Prius should be Rechargeable");
    }

    @Test
    @DisplayName("Prius is refuelable")
    void testPriusIsRefuelable() {
        assertInstanceOf(org.example.interfaces.Refuelable.class, prius, "A Prius should be Refuelable");
    }

}
