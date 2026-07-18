package org.example.Vehicle;

import org.example.vehicle.Engine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EngineTest {
    Engine engine;

    @BeforeEach
    void setUp() {
        engine = new Engine();
    }

    @Test()
    @DisplayName("Test Engine Start")
    void testEngineStart() {
        engine.start();
        assertTrue(engine.isRunning(),"Engine should be started");}

    @Test()
    @DisplayName("Test Engine Stop")
    void testEngineStop() {
        assertFalse(engine.isRunning(),"Engine should be stopped");
    }


    @Test()
    @DisplayName("Test Engine horsepower")
    void testEngineHorsepower() {
        engine.setHorsepower(200);
        assertEquals(200, engine.getHorsepower(), "Engine horsepower should be 200");
    }

    @Test
    @DisplayName("Test Engine Start when already running")
    void testEngineStartWhenAlreadyRunning() {
        engine.start();
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> engine.start(), "Should throw exception when starting an already running engine");
        assertEquals("Engine is already running.", exception.getMessage(), "Should throw exception when starting an already running engine");
    }

    @Test
    @DisplayName("Test Engine Stop when not running")
    void testEngineStopWhenNotRunning() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> engine.stop(), "Should throw exception when stopping an engine that is not running");
        assertEquals("Engine is not running.", exception.getMessage(), "Should throw exception when stopping an engine that is not running");
    }

    @Test
    @DisplayName("New engine is not running")
    void testInitialEngineNotRunning() {
        assertFalse(engine.isRunning(), "A fresh engine should not be running");
    }

    @Test
    @DisplayName("Engine can be restarted after stopping")
    void testEngineStartStopCycle() {
        engine.start();
        engine.stop();
        engine.start();
        assertTrue(engine.isRunning(), "Engine should be running again after a start/stop/start cycle");
    }

    @Test
    @DisplayName("Test Engine set negative horsepower")
    void testSetNegativeHorsepower() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> engine.setHorsepower(-100), "Should throw exception when setting negative horsepower");
        assertEquals("Horsepower cannot be negative.", exception.getMessage(), "Should throw exception when setting negative horsepower");
    }
}
