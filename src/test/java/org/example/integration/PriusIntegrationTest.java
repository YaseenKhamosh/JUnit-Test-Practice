package org.example.integration;

import org.example.factory.PriusFactory;
import org.example.integration.support.InMemoryVehicleRegistry;
import org.example.vehicle.Prius;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DisplayName("Prius integration")
public class PriusIntegrationTest {

    private static final double TANK_CAPACITY = 50.0;

    private static InMemoryVehicleRegistry registry;

    private Prius prius;

    @BeforeAll
    static void connectRegistry() {
        System.out.println("[BeforeAll] opening the shared registry (runs once)");
        registry = new InMemoryVehicleRegistry();
        registry.connect();
    }

    @AfterAll
    static void disconnectRegistry() {
        System.out.println("[AfterAll] closing the shared registry (runs once)");
        registry.disconnect();
    }

    @BeforeEach
    void resetAndBuildPrius() {
        registry.clear();
        prius = PriusFactory.createPrius();
        prius.getFuelTank().setFuelCapacity(TANK_CAPACITY);
        registry.save(prius);
    }

    @AfterEach
    void logAfterEach() {
        System.out.println("[AfterEach] test done; registry holds " + registry.count() + " car(s)");
    }


    @Nested
    @DisplayName("Style 1: grouped assertions (assertAll)")
    class GroupedAssertions {

        @Test
        @DisplayName("Refuel + recharge fill fuel and charge together")
        void testRefuelAndRecharge() {
            prius.refuel(40);
            prius.recharge(20);

            assertAll("hybrid holds both fuel and charge",
                    () -> assertEquals(40, prius.getFuelTank().getCurrentFuelLevel(), "fuel level"),
                    () -> assertEquals(20, prius.getBattery().getChargeLevel(), "charge level")
            );
        }

        @Test
        @DisplayName("Full drive workflow ends in the expected combined state")
        void testFullDriveWorkflow() {
            drive(prius);

            assertAll("combined state after a full drive",
                    () -> assertEquals(40, prius.getCurrentSpeed(), "speed"),
                    () -> assertEquals(20, prius.getFuelTank().getCurrentFuelLevel(), "fuel"),
                    () -> assertFalse(prius.getEngine().isRunning(), "engine off")
            );
        }
    }


    @Nested
    @DisplayName("Style 2: one assertion per test")
    class OneAssertionPerTest {

        @BeforeEach
        void driveTheCar() {
            drive(prius);
        }

        @Test
        @DisplayName("Speed is 40 after accelerating 60 and braking 20")
        void testSpeed() {
            assertEquals(40, prius.getCurrentSpeed());
        }

        @Test
        @DisplayName("Fuel is 20 after burning 10 of 30")
        void testFuel() {
            assertEquals(20, prius.getFuelTank().getCurrentFuelLevel());
        }

        @Test
        @DisplayName("Engine is off after the drive")
        void testEngineOff() {
            assertFalse(prius.getEngine().isRunning());
        }

        @Test
        @DisplayName("The Prius is retrievable from the registry by plate")
        void testRetrievableByPlate() {
            assertNotNull(registry.findByPlate("ABC123"));
        }
    }

    private static void drive(Prius prius) {
        prius.refuel(30);
        prius.getEngine().setHorsepower(120);
        prius.getEngine().start();
        prius.accelerate(60);
        prius.getFuelTank().consumeFuel(10);
        prius.brake(20);
        prius.getEngine().stop();
    }
}
