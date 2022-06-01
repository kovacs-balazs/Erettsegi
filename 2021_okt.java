package me.koba1.macro.erettsegi;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class okt2021 {

    public static ArrayList<String> txt = new ArrayList<>();
    public static int currentlyNumber = 0;
    public static Scanner scanner;
    public static int resztablazat = 0;

    public static void main(String[] args) {
        try {
            System.out.println("1. feladat");
            scanner = new Scanner(System.in);
            System.out.println("Adja meg a bemeneti fájl nevét!");
            String inFile = scanner.nextLine();

            File file = new File("D:\\" + inFile);

            Scanner reader = new Scanner(new FileReader(file));
            while(reader.hasNext()) {
                String line = reader.nextLine();
                txt.add(line);
            }

            reader.close();
            Task_2();
            Task_3();
            Task_4();
            Task_5();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void Task_2() {
        scanner = new Scanner(System.in);
        int inputRow = 0;
        int inputColumn = 0;
        System.out.println("Adja meg egy sor számát!");
        while (scanner.hasNext()) {
            inputRow = scanner.nextInt();

            System.out.println("Adja meg egy oszlop számát!");
            inputColumn = scanner.nextInt();
            break;
        }

        int counter = 1;
        for (String line : txt) {
            if (line.length() < 9) continue;
            if (inputRow <= 9 && inputRow > 0) {
                if (inputColumn <= 9 && inputColumn > 0) {
                    if (counter == inputRow) {
                        currentlyNumber = Integer.parseInt(line.split(" ")[inputColumn - 1]);

                        resztablazat = getResztablazat(inputColumn, inputRow);
                    }
                }
            }
            counter++;
        }
    }

    public static void Task_3() {
        System.out.println("\n3. feladat");
        System.out.println((currentlyNumber == 0) ? "Az adott helyet még nem töltötték ki." : "Az adott helyen szereplő szám: " + currentlyNumber);
        System.out.println("A hely a(z) " + resztablazat + " résztáblázathoz tartozik.");
    }

    public static void Task_4() {
        int nulla = 0;
        for(String line : txt) {
            String[] args = line.split(" ");
            for(String arg : args) {
                if(arg.equalsIgnoreCase("0")) {
                    nulla++;
                }
            }
        }
        System.out.println("\n4. feladat");
        System.out.println("Az üres helyek aránya: " + format.format(nulla / 81.0 * 100).replace(",", ".") + "%");
    }

    public static void Task_5() {
        ArrayList<String> nonSudoku = new ArrayList<>();
        for(String str : txt) {
            if(str.length() < 9) {
                nonSudoku.add(str);
            }
        }

        System.out.println("\n5. feladat");
        for(String str : nonSudoku) {
            String[] args = str.split(" ");

            int row = Integer.parseInt(args[1]);
            int column = Integer.parseInt(args[2]);
            int number = Integer.parseInt(args[0]);

            System.out.println("A kiválasztott sor: " + row + " oszlop: " + column + " a szám: " + number);
            if(getLine(row).split(" ")[column - 1].equals("0")) { // lépés megtehető
                if (getColumnNumbers(column).contains(String.valueOf(number))) { // oszlopban van-e
                    System.out.println("Az adott oszlopban már szerepel a szám.");
                    return;
                }
                if (!(resztablazatNumbers(row, column).contains(String.valueOf(number))))
                    System.out.println("A lépés megtehető.");
                else
                    System.out.println("A résztáblázatban már szerepel a szám");
            } else
                System.out.println("A helyet már kitöltötték!");

            System.out.println(" ");
        }
    }

    public static ArrayList<String> resztablazatNumbers(int inputRow, int inputColumn) {
        int resztablazat = getResztablazat(inputColumn, inputRow);

        ArrayList<String> list = new ArrayList<>();

        int counter = 1;
        for(String line : txt) {
            if(line.length() < 9) continue;
            if(getLines(resztablazat).contains(counter)) {
                for(int integer : getColumn(resztablazat)) {
                    list.add(line.split(" ")[integer]);
                }
            }
            else if(getLines(resztablazat).contains(counter)) {
                for(int integer : getColumn(resztablazat)) {
                    list.add(line.split(" ")[integer]);
                }
            }
            else if(getLines(resztablazat).contains(counter)) {
                for(int integer : getColumn(resztablazat)) {
                    list.add(line.split(" ")[integer]);
                }

                return list;
            }
            counter++;
        }

        return list;
    }

    public static String getLine(int row) {
        int counter = 1;
        for(String line : txt) {
            if(counter == row)
                return line;

            counter++;
        }

        return null;
    }

    public static ArrayList<String> getColumnNumbers(int column) {
        ArrayList<String> returnList = new ArrayList<>();
        for(String str : txt) {
            if(str.length() < 9) continue;
            returnList.add(str.split(" ")[column - 1]);
        }

        return returnList;
    }

    public static ArrayList<Integer> getColumn(int resztablazat) {
        ArrayList<Integer> returnList = new ArrayList<>();
        switch (resztablazat) {
            case 1:
            case 4:
            case 7:
                returnList.add(0);
                returnList.add(1);
                returnList.add(2);
                break;
            case 2:
            case 5:
            case 8:
                returnList.add(3);
                returnList.add(4);
                returnList.add(5);
                break;
            case 3:
            case 6:
            case 9:
                returnList.add(6);
                returnList.add(7);
                returnList.add(8);
                break;
            default:
                return returnList;
        }

        return  returnList;
    }

    public static ArrayList<Integer> getLines(int resztablazat) {
        ArrayList<Integer> returnList = new ArrayList<>();
        switch (resztablazat) {
            case 1:
            case 2:
            case 3:
                returnList.add(0);
                returnList.add(1);
                returnList.add(2);
                break;
            case 4:
            case 5:
            case 6:
                returnList.add(3);
                returnList.add(4);
                returnList.add(5);
                break;
            case 7:
            case 8:
            case 9:
                returnList.add(6);
                returnList.add(7);
                returnList.add(8);
                break;
            default:
                return returnList;
        }

        return  returnList;
    }

    private static final DecimalFormat format = new DecimalFormat("0.0");

    public static int getResztablazat(int inputColumn, int inputRow) {
        int resztablazat = 0;
        if (inputColumn / 3.0 <= 1.0) { // 1, 4, 7
            if (inputRow / 3.0 <= 1.0) // 1
                resztablazat = 1;
            else if (inputRow / 3.0 <= 2.0) // 4
                resztablazat = 4;
            else if (inputRow / 3.0 <= 3.0) // 7
                resztablazat = 7;
        }
        //
        else if (inputColumn / 3.0 <= 2.0 && inputColumn / 3.0 > 1.0) { // 2, 5, 8
            if (inputRow / 3.0 <= 1.0) // 2
                resztablazat = 2;
            else if (inputRow / 3.0 <= 2.0) // 5
                resztablazat = 5;
            else if (inputRow / 3.0 <= 3.0) // 8
                resztablazat = 8;
        }
        //
        else if (inputColumn / 3.0 <= 3.0 && inputColumn / 3.0 > 2.0) { // 3, 6, 9
            if (inputRow / 3.0 <= 1.0) // 3
                resztablazat = 3;
            else if (inputRow / 3.0 <= 2.0) // 6
                resztablazat = 6;
            else if (inputRow / 3.0 <= 3.0) // 9
                resztablazat = 9;
        }
        return resztablazat;
    }
}
