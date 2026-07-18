package org.example;

import org.example.enums.Color;
import org.example.vehicle.Battery;
import org.example.vehicle.Engine;
import org.example.vehicle.FuelTank;
import org.example.vehicle.Prius;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.car();
    }

    public void car(){
        Prius prius = new Prius(
                2020,
                Color.WHITE,
                25000.0,
                "Toyota",
                "Prius",
                "ABC123",
                new Engine(),
                new FuelTank(),
                new Battery()
        );
        prius.drive();
        prius.park();
    }

}