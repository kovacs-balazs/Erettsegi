package me.koba1.cegesautok.feladatok;

import me.koba1.cegesautok.Car;
import me.koba1.cegesautok.Main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HarmadikFeladat implements Feladat{
    @Override
    public int getNumber() {
        return 4;
    }

    @Override
    public void run() {
        Map<String, Car> cars = new HashMap<>();
        for (Car car : Main.getInstance().getCars()) {
            cars.put(car.getPlate(), car);
        }

        List<Car> withdrawedCars = cars.values().stream().filter(c -> !c.isDeposit()).toList();
        System.out.println("A hónap végén " + withdrawedCars.size() + " autót nem hoztak vissza.");
    }
}
