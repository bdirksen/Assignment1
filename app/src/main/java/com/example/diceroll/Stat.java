package com.example.diceroll;

public class Stat {
    private final String amount;
    private final String roll;

    public Stat(String amount, String roll){
        this.amount = amount;
        this.roll = roll;
    }

    public String getAmount() {
        return amount;
    }

    public String getRoll() {
        return roll;
    }
}
