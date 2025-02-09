package me.koba1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class Main {

    private final List<Tabor> taborok;

    public Main() throws Exception {
        this.taborok = new ArrayList<>();
        File taborok = new File("./2023maj/taborok.txt");

        Scanner in = new Scanner(taborok);
        while (in.hasNextLine()) {
            String line = in.nextLine();

            String[] args = line.split("\t");

            this.taborok.add(new Tabor(
                    Integer.parseInt(args[0]),
                    Integer.parseInt(args[1]),
                    Integer.parseInt(args[2]),
                    Integer.parseInt(args[3]),
                    args[4],
                    args[5]
            ));
        }

        masodikFeladat();
        harmadikFeladat();
        negyedikFeladat();
        hatodikFeladat();
        hetedikFeladat();
    }

    public void masodikFeladat() {
        System.out.println("2. feladat");
        System.out.println("Az adatsorok száma: " + taborok.size());
        System.out.println("Az először rögzített tábor témája: " + taborok.getFirst().getTaborTheme());
        System.out.println("Az utoljára rögzített tábor témája: " + taborok.getLast().getTaborTheme());
    }

    public void harmadikFeladat() {
        System.out.println("3. feladat");
        List<Tabor> zeneiTaborok = this.taborok.stream().filter(tabor -> tabor.getTaborTheme().equals("zenei")).toList();
        if (zeneiTaborok.isEmpty()) {
            System.out.println("Nem volt zenei tábor.");
            return;
        }

        for (Tabor tabor : zeneiTaborok) {
            System.out.println("Zenei tábor kezdődik " + tabor.getStartMonth() + ". hó " + tabor.getStartDay() + ". napján.");
        }
    }

    public void negyedikFeladat() {
        System.out.println("4. feladat");

        Tabor tabor = this.taborok.get(0);
        for (Tabor el : this.taborok) {
            if (el.getTanulok().length() > tabor.getTanulok().length()) {
                tabor = el;
            }
        }

        for (Tabor tabor1 : this.taborok) {
            if (tabor1.getTanulok().length() == tabor.getTanulok().length()) {
                System.out.println(tabor.getStartMonth() + " " + tabor.getStartDay() + " " + tabor.getTaborTheme());
            }
        }
    }

    // otodik feladat
    public int sorszam(int month, int day) {
        if (month == 6) {
            return day - 15;
        } else if (month == 7) {
            return day + 15;
        } else {
            return day + 46;
        }
    }

    public void hatodikFeladat() throws ParseException {
        System.out.println("6. feladat");
        Scanner month = new Scanner(System.in);
        System.out.print("hó: ");
        int monthNum = month.nextInt();

        Scanner day = new Scanner(System.in);
        System.out.print("nap: ");
        int dayNum = day.nextInt();

        List<Tabor> taborok = new ArrayList<>();

        for (Tabor tabor : this.taborok) {
            if(between(tabor, monthNum, dayNum)) {
                taborok.add(tabor);
            }
        }

        System.out.println("Ekkor éppen " + taborok.size() + " tábor tart.");
    }

    private boolean between(Tabor tabor, int month, int day) {

        int start = sorszam(tabor.getStartMonth(), tabor.getStartDay());
        int end = sorszam(tabor.getEndMonth(), tabor.getEndDay());
        int sorszam = sorszam(month, day);
        return start <= sorszam && end >= sorszam;
    }

    public void hetedikFeladat() throws IOException {
        System.out.println("7. feladat");
        Scanner scan = new Scanner(System.in);

        System.out.print("Adja meg egy tanuló betűjelét: ");
        String tanulo = scan.nextLine();

        List<Tabor> taborok = new ArrayList<>();
        for (Tabor tabor : this.taborok) {
            if (!tabor.getTanulok().contains(tanulo.toUpperCase())) continue;

            taborok.add(tabor);
        }

        File egytanulo = new File("./2023maj/egytanulo.txt");

        BufferedWriter writer = new BufferedWriter(new FileWriter(egytanulo));

        taborok.sort(Comparator.comparing(Tabor::getStartMonth).thenComparing(Tabor::getStartDay));
        for (Tabor tabor : taborok) {
            writer.append(tabor.getStartMonth()+"."+tabor.getStartDay() + "-" + tabor.getEndMonth()+"."+tabor.getEndDay() + ". " + tabor.getTaborTheme() + "\n");
        }

        writer.close();

        boolean success = true;
        outer: for (Tabor tabor : taborok) {
            for (Tabor tabor1 : taborok) {
                if (!isElTudMenni(tabor, tabor1)) {
                    System.out.println("Nem tud elmenni mindegyik táborba.");
                    success = false;
                    break outer;
                }
            }
        }
        if(success) System.out.println("Mindegyik táborban részt vehet.");
    }

    private boolean isElTudMenni(Tabor tabor1, Tabor tabor2) {
        int sorszam2 = sorszam(tabor1.getEndMonth(), tabor1.getEndDay());
        int sorszam = sorszam(tabor2.getStartMonth(), tabor2.getStartDay());
        return sorszam2 > sorszam;
    }

    public static void main(String[] args) throws Exception {
        new Main();
    }
}
