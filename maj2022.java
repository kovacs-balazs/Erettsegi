package me.koba1.emelt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class maj2022 {

    public static ArrayList<String> txt = new ArrayList();
    public static int aPrice;
    public static int bPrice;
    public static int cPrice;

    public static void main(String[] args) {
        File file = new File("D:\\utca.txt");
        try {
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()) {
                String nextline = reader.nextLine();
                if(nextline.split(" ").length == 3) {
                    aPrice = Integer.parseInt(nextline.split(" ")[0]);
                    bPrice = Integer.parseInt(nextline.split(" ")[1]);
                    cPrice = Integer.parseInt(nextline.split(" ")[2]);
                    continue;
                }
                txt.add(nextline);
            }

            System.out.println("2. feladat. A mintában " + txt.size() + " telek szerepel.");
            reader.close();
            Task_3();
            Task_5();
            Task_6();
            Task_7();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void Task_5() {
        HashMap<String, Integer> telkekA = new HashMap<>();
        HashMap<String, Integer> telkekB = new HashMap<>();
        HashMap<String, Integer> telkekC = new HashMap<>();

        for(String line : txt) {
            String[] lines = line.split(" ");
            if(line.split(" ")[3].equalsIgnoreCase("a"))
                telkekA.put(line, ado(lines[3], Integer.parseInt(lines[4])));

            if(line.split(" ")[3].equalsIgnoreCase("b"))
                telkekB.put(line, ado(lines[3], Integer.parseInt(lines[4])));

            if(line.split(" ")[3].equalsIgnoreCase("c"))
                telkekC.put(line, ado(lines[3], Integer.parseInt(lines[4])));
        }

        System.out.println("5. feladat");
        System.out.println("A sávba " + telkekA.size() + " telek esik, az adó " + getAdoOfSav(telkekA) + " Ft.");
        System.out.println("B sávba " + telkekB.size() + " telek esik, az adó " + getAdoOfSav(telkekB) + " Ft.");
        System.out.println("C sávba " + telkekC.size() + " telek esik, az adó " + getAdoOfSav(telkekC) + " Ft.");
    }

    public static void Task_6() {
        HashMap<String, String> telek = new HashMap<>();
        ArrayList list = new ArrayList();
        System.out.println("6. feladat. A több sávba sorolt utcák:");
        for(String line : txt) {
            if(telek.containsKey(line.split(" ")[1])) {
                if(telek.get(line.split(" ")[1]).equalsIgnoreCase(line.split(" ")[3]))
                    continue;

                if(!list.contains(line.split(" ")[1]))
                    list.add(line.split(" ")[1]);

                telek.put(line.split(" ")[1], line.split(" ")[3]);
                continue;
            }
            telek.put(line.split(" ")[1], line.split(" ")[3]);
        }
        list.forEach(line -> System.out.println(line));
    }

    public static int getAdoOfSav(HashMap<String, Integer> hashMap) {
        int ado = 0;
        for(Map.Entry<String, Integer> telek : hashMap.entrySet())
            ado += telek.getValue();

        return ado;
    }

    public static String str = "";
    public static void Task_7() {
        //       owner,  összeg
        LinkedHashMap<String, Integer> owners = new LinkedHashMap<>();
        for(String line : txt) {
            String[] splits = line.split(" ");

            String owner = splits[0];
            int adoToTelek = ado(splits[3], Integer.parseInt(splits[4]));

            int telek = (owners.get(owner) == null) ? adoToTelek : owners.get(owner) + adoToTelek;

            if(owners.containsKey(owner))
                owners.replace(owner, telek);
            else
                owners.put(owner, telek);
        }

        for(Map.Entry<String, Integer> hashMapOwners : owners.entrySet()) {
            str += hashMapOwners.getKey() + " " + hashMapOwners.getValue() + "\n";
        }
        append(str);
    }

    private static void append(String line) {
        try {
            Files.write(Paths.get("D:\\fizetendo.txt"), line.getBytes(), StandardOpenOption.WRITE);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void Task_3() {
        Scanner scn = new Scanner(System.in);
        String inLine = scn.nextLine();

        boolean contains = false;

        for(String txtLine : txt) {
            if(txtLine.contains(inLine) && inLine.length() == txtLine.length())
                contains = true;
        }

        if(!contains) {
            System.out.println("Nem szerepel az adatállományban.");
            return;
        }

        System.out.println("3. feladat. Egy tulajdonos adószáma: " + inLine);
        for(String lines : txt) {
            if(!lines.startsWith(inLine))
                continue;

            System.out.println(lines.split(" ")[1] + " utca " + lines.split(" ")[2]);
        }
    }

    public static int ado(String adosav, int telekmeret) {
        int fizetendo = 0;

        if(adosav.equalsIgnoreCase("a"))
            fizetendo = telekmeret * aPrice;
        else if(adosav.equalsIgnoreCase("b"))
            fizetendo = telekmeret * bPrice;
        else if(adosav.equalsIgnoreCase("c"))
            fizetendo = telekmeret * cPrice;

        return (fizetendo >= 10000) ? fizetendo : 0;
    }
}
