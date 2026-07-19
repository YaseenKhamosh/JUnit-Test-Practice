package org.example.integration.support;

import org.example.vehicle.Prius;

import java.util.HashMap;
import java.util.Map;

public class InMemoryVehicleRegistry {

    private Map<String, Prius> store;
    private boolean connected;

    public void connect() {
        store = new HashMap<>();
        connected = true;
        System.out.println(">>> Registry connected");
    }

    public void disconnect() {
        store = null;
        connected = false;
        System.out.println(">>> Registry disconnected");
    }

    public boolean isConnected() {
        return connected;
    }

    public void save(Prius prius) {
        requireConnected();
        store.put(prius.getLicensePlate(), prius);
    }

    public Prius findByPlate(String plate) {
        requireConnected();
        return store.get(plate);
    }

    public int count() {
        requireConnected();
        return store.size();
    }

    public void clear() {
        requireConnected();
        store.clear();
    }

    private void requireConnected() {
        if (!connected) {
            throw new IllegalStateException("Registry is not connected.");
        }
    }
}
