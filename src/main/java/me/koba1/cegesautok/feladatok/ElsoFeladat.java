package me.koba1.cegesautok.feladatok;

import me.koba1.cegesautok.Car;
import me.koba1.cegesautok.Main;

import java.util.List;

public class ElsoFeladat implements Feladat {
    @Override
    public int getNumber() {
        return 2;
    }

    @Override
    public void run() {
        List<Car> withdrawedCars = Main.getInstance().getCars().stream().filter(c -> !c.isDeposit()).toList();
        //30. nap rendszám: CEG300
        Car lastCar = withdrawedCars.getLast();
        System.out.println(lastCar.getDayOfMonth() + ". nap rendszám: " + lastCar.getPlate());
    }
}