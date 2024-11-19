package me.koba1.cegesautok;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Car {

    private int dayOfMonth;
    private String time;
    private String plate;
    private int id;
    private int km;
    private boolean deposit; /// 0 = kiviszik false 1 = behozzák true
}