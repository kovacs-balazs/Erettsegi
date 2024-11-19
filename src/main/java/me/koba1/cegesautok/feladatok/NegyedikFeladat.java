package me.koba1.cegesautok.feladatok;

import me.koba1.cegesautok.Main;

import java.util.HashMap;
import java.util.Map;

public class NegyedikFeladat implements Feladat {
    @Override
    public int getNumber() {
        return 5;
    }

    @Override
    public void run() {
        Map<String, Integer> kms = new HashMap<>();

        for (Main.CarPair carPair : Main.getInstance().getCarPairs()) {
            if(carPair.getCarIn() == null) continue;
            if(kms.containsKey(carPair.getCarOut().getPlate())) continue;

            kms.put(
                    carPair.getCarOut().getPlate(),
                    Main.getInstance().getCarPairs().stream()
                            .filter(p -> p.getCarOut().getPlate().equals(carPair.getCarOut().getPlate()))
                            .mapToInt(Main.CarPair::getDistanceTraveled)
                            .sum()
            );
        }
        for (Map.Entry<String, Integer> entry : kms.entrySet()) {
            //CEG303 7465 km
            System.out.println(entry.getKey() + " " + entry.getValue() + " km");
        }
    }
}
