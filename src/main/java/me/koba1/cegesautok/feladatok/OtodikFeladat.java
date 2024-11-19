package me.koba1.cegesautok.feladatok;

import me.koba1.cegesautok.Main;

import java.util.Comparator;

public class OtodikFeladat implements Feladat{
    @Override
    public int getNumber() {
        return 6;
    }

    @Override
    public void run() {
        Main.getInstance().getCarPairs().stream().sorted(Comparator.comparingInt(Main.CarPair::getDistanceTraveled).reversed()).findAny().ifPresent(pair -> System.out.println("Leghosszabb út: " + pair.getDistanceTraveled() + " km, személy: " + pair.getCarOut().getId()));
    }
}
