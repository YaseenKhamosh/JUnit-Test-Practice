package org.example.Vehicle;

import org.example.vehicle.Battery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BatteryTest {
    Battery battery;
    @BeforeEach
    void setUp() {
       battery = new Battery();
    }

    @Test
    @DisplayName("Charge Battery")
    void testChargeBattery() {
        battery.charge(10);
        assertEquals(10, battery.getChargeLevel(), "Battery should be charged to 10 kWh");
    }

    @Test
    @DisplayName("Charge Battery with negative value")
    void testChargeBatteryNegativeValue() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> battery.charge(-5), "Should throw exception when charging with negative value");
        assertEquals("Charge amount must be positive.", exception.getMessage(), "Should throw exception when charging with negative value");
    }

    @Test
    @DisplayName("Drain Battery")
    void testDrainBattery() {
        battery.charge(10);
        battery.drain(5);
        assertEquals(5, battery.getChargeLevel(), "Battery should be drained to 5 kWh");
    }

    @Test
    @DisplayName("Drain Battery more than available")
    void testDrainBatteryMoreThanAvailable() {
        battery.charge(5);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> battery.drain(10), "Should throw exception when draining more than available");
        assertEquals("Cannot drain battery: not enough charge available.", exception.getMessage(), "Should throw exception when draining more than available");
    }

    @Test
    @DisplayName("Drain Battery with negative value")
    void testDrainBatteryNegativeValue() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> battery.drain(-5), "Should throw exception when draining with negative value");
        assertEquals("Drain amount must be positive.", exception.getMessage(), "Should throw exception when draining with negative value");
    }

    @Test
    @DisplayName("New battery starts at zero charge")
    void testInitialChargeLevelIsZero() {
        assertEquals(0, battery.getChargeLevel(), "A fresh battery should have 0 kWh");
    }

    @Test
    @DisplayName("Drain exactly to zero is allowed (boundary)")
    void testDrainExactlyToZero() {
        battery.charge(10);
        battery.drain(10);
        assertEquals(0, battery.getChargeLevel(), "Draining the full charge should leave exactly 0 kWh");
    }

    @Test
    @DisplayName("Multiple charges accumulate")
    void testMultipleChargesAccumulate() {
        battery.charge(5);
        battery.charge(7);
        assertEquals(12, battery.getChargeLevel(), "Two charges of 5 and 7 should total 12 kWh");
    }
}
