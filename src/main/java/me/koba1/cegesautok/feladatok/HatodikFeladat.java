package me.koba1.cegesautok.feladatok;

import me.koba1.cegesautok.Main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HatodikFeladat implements Feladat {
    @Override
    public int getNumber() {
        return 7;
    }

    @Override
    public void run() {
        System.out.println("Rendszám: ");
        Scanner scn = new Scanner(System.in);
        if (scn.hasNext()) {
            String plate = scn.nextLine();
            List<String> lines = new ArrayList<>();

            for (Main.CarPair pair : Main.getInstance().getCarPairs().stream().filter(p -> p.getCarOut().getPlate().equals(plate)).toList()) {
                StringBuilder builder = new StringBuilder()
                        .append(pair.getCarOut().getId())
                        .append("\t")
                        .append(pair.getCarOut().getDayOfMonth())
                        .append(". ")
                        .append(pair.getCarOut().getTime())
                        .append("\t")
                        .append(pair.getCarOut().getKm())
                        .append(" km");
                if (pair.getCarIn() != null) {
                    builder.append("\t")
                            .append(pair.getCarIn().getDayOfMonth())
                            .append(". ")
                            .append(pair.getCarIn().getTime())
                            .append("\t")
                            .append(pair.getCarIn().getKm())
                            .append(" km");

                }

                builder.append("\n");
                lines.add(builder.toString());
            }
            if (lines.isEmpty()) return;
            writeToFile(plate, lines);
        }
    }

    private void writeToFile(String plate, List<String> lines) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(plate + "_menetlevel.txt"));
            for (String line : lines) {
                writer.write(line);
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}