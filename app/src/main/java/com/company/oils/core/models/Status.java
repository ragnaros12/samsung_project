package com.company.oils.core.models;

import android.graphics.Color;

public enum Status {
    WAIT(Color.GRAY, "Ожидание"),
    PAYED(Color.YELLOW, "Оплачен"),
    GET_PAYED(Color.GREEN, "Оплачен и выдан"),
    GET(Color.RED, "Выдан но не оплачен");


    private final int color;
    private final String text;

    public int getColor(){
        return color;
    }

    public String getText(){
        return text;
    }

    Status(int color, String text) {
        this.color = color;
        this.text = text;
    }
}
