package me.koba1;

public class Tabor {

    public int getStartMonth() {
        return startMonth;
    }

    public int getStartDay() {
        return startDay;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public int getEndDay() {
        return endDay;
    }

    public String getTanulok() {
        return tanulok;
    }

    public String getTaborTheme() {
        return taborTheme;
    }

    private int startMonth;
    private int startDay;
    private int endMonth;
    private int endDay;
    private String tanulok;
    private String taborTheme;

    public Tabor(int startMonth, int startDay, int endMonth, int endDay, String tanulok, String taborTheme) {
        this.startMonth = startMonth;
        this.startDay = startDay;
        this.endMonth = endMonth;
        this.endDay = endDay;
        this.tanulok = tanulok;
        this.taborTheme = taborTheme;
    }
}
