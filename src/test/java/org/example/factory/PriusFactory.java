package org.example.factory;

import org.example.enums.Color;
import org.example.vehicle.Battery;
import org.example.vehicle.Engine;
import org.example.vehicle.FuelTank;
import org.example.vehicle.Prius;

public  class PriusFactory {

    public static Prius createPrius() {
        return new Prius(
                2022,
                Color.WHITE,
                25000.0,
                "Toyota",
                "Prius",
                "ABC123",
                new Engine(),
                new FuelTank(),
                new Battery()
        );
    }
}
