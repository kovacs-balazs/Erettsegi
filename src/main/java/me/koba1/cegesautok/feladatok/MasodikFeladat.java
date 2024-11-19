package me.koba1.cegesautok.feladatok;

import me.koba1.cegesautok.Car;
import me.koba1.cegesautok.Main;

import java.util.List;
import java.util.Scanner;

public class MasodikFeladat implements Feladat{
    @Override
    public int getNumber() {
        return 3;
    }

    @Override
    public void run() {
        System.out.println("Nap: ");
        //Scanner scn = new Scanner(System.in);

        //if (scn.hasNext()) {
        if(true){
            String line = "4"; //scn.nextLine();

            int day = Integer.parseInt(line);
            List<Car> daycars = Main.getInstance().getCars().stream().filter(c -> c.getDayOfMonth() == day).toList();
         /*   Forgalom a(z) 4. napon:
            12:50 CEG303 561 ki
            19:17 CEG308 552 be */
            System.out.println("Forgalom a(z) " + day + ". napon:");
            for (Car daycar : daycars) {
                System.out.println(daycar.getTime() + " " + daycar.getPlate() + " " + daycar.getId() + " " + (daycar.isDeposit() ? "be" : "ki"));
            }
        }
    }
}
