package me.koba1;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    private final List<Card> cards;

    public Main() throws Exception {
        cards = new ArrayList<Card>();

        SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
        try {
            Scanner sc = new Scanner(new FileReader("./2024maj/bedat.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] split = line.split(" ");

                Date date = sf.parse(split[1]);
                Card.CardAction action = Card.CardAction.getByValue(Integer.parseInt(split[2]));

                cards.add(new Card(split[0], date, action));
            }
        } catch (FileNotFoundException | ParseException e) {
            throw new RuntimeException(e);
        }

        if (this.cards.isEmpty()) return;


        masodikFeladat();
        harmadikFeladat();
        negyedikFeladat();
        otodikFeladat();
        hatodikFeladat();
        hetedikFeladat();
    }

    public void masodikFeladat() {
        Card elso = this.cards.getFirst();
        Card utolso = this.cards.getLast();

        System.out.println("2. feladat");
        System.out.println("Az első tanuló " + elso.getFormattedTime() + "-kor lépett be a főkapun.");
        System.out.println("Az első tanuló " + utolso.getFormattedTime() + "-kor lépett ki a főkapun.");
    }

    public void harmadikFeladat() throws Exception {

        File file = new File("./2024maj/kesok.txt");

        List<Card> tanulok = new ArrayList<>();
        for (Card card : this.cards) {
            if (between(card.getFormattedTime(), "07:50", "08:15")) {
                tanulok.add(card);
            }
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for (Card tanulo : tanulok) {
            writer.append(tanulo.getFormattedTime() + " " + tanulo.getCode() + "\n");
        }
        writer.close();
    }

    public void negyedikFeladat() {
        System.out.println("4. feladat");

        System.out.println("A menzán aznap " + this.cards.stream().filter(c -> c.getAction() == Card.CardAction.EBED).count() + " tanuló ebédelt.");
    }

    public void otodikFeladat() {
        System.out.println("5. feladat");

        long ebed = this.cards.stream().filter(c -> c.getAction() == Card.CardAction.EBED).count();
        List<Card> kolcsonzesek = new ArrayList<>();
        for (Card card : this.cards) {
            if (kolcsonzesek.stream().map(Card::getCode).toList().contains(card.getCode())) continue;
            if (card.getAction() == Card.CardAction.KOLCSONZES) {
                kolcsonzesek.add(card);
            }
        }
        int kolcsonzes = kolcsonzesek.size();

        System.out.println("Aznap " + kolcsonzes + " tanuló kölcsönzött a könyvtárban.");
        if (kolcsonzes < ebed) {
            System.out.println("Nem voltak többen, mint menzán.");
        } else {
            System.out.println("Többen voltak, mint a menzán.");
        }
    }

    public void hatodikFeladat() throws ParseException {
        System.out.println("6. feladat");
        System.out.println("Érintett tanulók:");

        List<Card> szokottTanulok = new ArrayList<>();

        for (Card card : this.cards) {
            if (card.getAction() == Card.CardAction.BELEPES) {
                if (between(card.getFormattedTime(), "10:51", "10:59")) {
                    if (alreadyEntered(card.getCode())) {
                        szokottTanulok.add(card);
                    }
                }
            }
        }

        System.out.println(String.join(" ", szokottTanulok.stream().map(Card::getCode).toList()));
    }

    public void hetedikFeladat() {
        Scanner scn = new Scanner(System.in);
        System.out.println("7. feladat");
        System.out.print("Egy tanuló azonosítója=");

        String code = scn.nextLine();

        Card enter = this.cards.stream().filter(c -> c.getCode().equals(code) && c.getAction() == Card.CardAction.BELEPES).findFirst().orElse(null);
        if(enter == null) {
            System.out.println("Ilyen azonosítójú tanuló aznap nem volt az iskolában.");
            return;
        }

        Card leave = null;
        for (int i = this.cards.size() - 1; i >= 0; i--) {
            if(this.cards.get(i).getCode().equals(code) && this.cards.get(i).getAction() == Card.CardAction.KILEPES) {
                leave = this.cards.get(i);
                break;
            }
        }

        long diff = leave.getTime().getTime() - enter.getTime().getTime();
        int secs = (int) (diff / 1000);
        int hour = (secs / 60 / 60) % 24;
        secs -= hour * 60 * 60;
        int mins = secs / 60;
        System.out.println("A tanuló érkezése és távozása között " + hour + " óra " + mins + " perc telt el.");
    }

    private boolean alreadyEntered(String code) {
        boolean entered = false;
        for (Card card : this.cards) {
            if (!code.equals(card.getCode())) continue;
            if (between(card.getFormattedTime(), "00:00", "10:50")) {
                if (card.getAction() == Card.CardAction.BELEPES) {
                    entered = true;
                } else if (card.getAction() == Card.CardAction.KILEPES) {
                    entered = false;
                }

            }
        }
        return entered;
    }

    private boolean between(String time, String min, String max) {
        return time.compareTo(min) >= 0 && time.compareTo(max) <= 0;
    }

    public static void main(String[] args) throws Exception {
        new Main();
    }
}
