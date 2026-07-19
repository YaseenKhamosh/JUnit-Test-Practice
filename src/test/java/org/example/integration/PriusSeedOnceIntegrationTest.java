package org.example.integration;

import org.example.enums.Color;
import org.example.factory.PriusFactory;
import org.example.integration.support.InMemoryVehicleRegistry;
import org.example.vehicle.Prius;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Prius seed-once integration (insert once, read many)")
public class PriusSeedOnceIntegrationTest {

    private static final String SEEDED_PLATE = "ABC123";

    private static InMemoryVehicleRegistry registry;

    @BeforeAll
    static void connectAndSeed() {
        System.out.println("[BeforeAll] connect + insert the seed Prius (runs ONCE)");
        registry = new InMemoryVehicleRegistry();
        registry.connect();

        Prius seed = PriusFactory.createPrius();
        seed.getFuelTank().setFuelCapacity(50);
        seed.refuel(40);
        seed.recharge(25);
        seed.getEngine().setHorsepower(130);
        registry.save(seed);
    }

    private static Prius seededPrius() {
        return registry.findByPlate(SEEDED_PLATE);
    }

    @AfterAll
    static void disconnect() {
        System.out.println("[AfterAll] disconnect (runs ONCE)");
        registry.disconnect();
    }

    @Test
    @DisplayName("Prius is retrievable by plate")
    void testRetrievable() {
        assertNotNull(registry.findByPlate(SEEDED_PLATE), "seed inserted in @BeforeAll should be found");
    }

    @Test
    @DisplayName("holds exactly the one seeded car")
    void testExactlyOneCar() {
        assertEquals(1, registry.count(), "only the single @BeforeAll seed should exist");
    }

    @Test
    @DisplayName("Prius make is Toyota")
    void testMake() {
        assertEquals("Toyota", registry.findByPlate(SEEDED_PLATE).getMake());
    }

    @Test
    @DisplayName("Prius model is Prius")
    void testModel() {
        assertEquals("Prius", registry.findByPlate(SEEDED_PLATE).getModel());
    }

    @Test
    @DisplayName("Prius year is 2022")
    void testYear() {
        assertEquals(2022, registry.findByPlate(SEEDED_PLATE).getYear());
    }

    @Test
    @DisplayName("Prius color is white")
    void testColor() {
        assertEquals(Color.WHITE, registry.findByPlate(SEEDED_PLATE).getColor());
    }


    @Test
    @DisplayName("Seeded fuel tank capacity is 50")
    void testTankCapacity() {
        assertEquals(50, seededPrius().getFuelTank().getFuelCapacity());
    }

    @Test
    @DisplayName("Seeded fuel level is 40")
    void testTankFuelLevel() {
        assertEquals(40, seededPrius().getFuelTank().getCurrentFuelLevel());
    }

    @Test
    @DisplayName("Seeded battery charge is 25 kWh")
    void testBatteryCharge() {
        assertEquals(25, seededPrius().getBattery().getChargeLevel());
    }

    @Test
    @DisplayName("Seeded engine horsepower is 130")
    void testEngineHorsepower() {
        assertEquals(130, seededPrius().getEngine().getHorsepower());
    }

    @Test
    @DisplayName("Seeded engine is not running (never started)")
    void testEngineNotRunning() {
        assertFalse(seededPrius().getEngine().isRunning());
    }
}
