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
            if(!kms.containsKey(carPair.getCarOut().getPlate())) {
                kms.put(carPair.getCarOut().getPlate(), carPair.getDistanceTraveled());
            } else {
                int distance = kms.get(carPair.getCarOut().getPlate());
                distance += carPair.getDistanceTraveled();
                kms.put(carPair.getCarOut().getPlate(), distance);
            }
        }
        for (Map.Entry<String, Integer> entry : kms.entrySet()) {
            //CEG303 7465 km
            System.out.println(entry.getKey() + " " + entry.getValue() + " km");
        }
    }
}
