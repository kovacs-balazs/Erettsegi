package me.koba1;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Card {

    public static enum CardAction {
        BELEPES(1),
        KILEPES(2),
        EBED(3),
        KOLCSONZES(4);

        private final int value;

        CardAction(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static CardAction getByValue(int value) {
            for (CardAction cardAction : values()) {
                if(cardAction.getValue() == value) {
                    return cardAction;
                }
            }
            return null;
        }
    }

    public String getCode() {
        return code;
    }

    public Date getTime() {
        return time;
    }

    public CardAction getAction() {
        return action;
    }

    private String code;
    private Date time;
    private CardAction action;

    public Card(String code, Date time, CardAction action) {
        this.code = code;
        this.time = time;
        this.action = action;
    }

    public String getFormattedTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(time);
    }
}
