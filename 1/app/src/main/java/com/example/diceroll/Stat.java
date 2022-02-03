package com.example.diceroll;

public class Stat {
    private String amount;
    private String roll;

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

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }
}
