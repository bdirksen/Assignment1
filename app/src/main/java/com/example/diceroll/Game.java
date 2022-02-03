package com.example.diceroll;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
    private String name;
    private int rolls;
    private int sides;
    private ArrayList<Integer> rollList = new ArrayList<>();
    private int count = 0;

    public Game(String name, int rolls, int sides){
        this.name = name;
        this.rolls = rolls;
        this.sides = sides;
    }

    public String getName() {

        return name;
    }

    public int getRolls() {

        return rolls;
    }

    public int getSides() {

        return sides;
    }

    public int getCount() {
        return count;
    }

    public void addToCount() {
        this.count++;
    }

    public ArrayList<Integer> getRollList() {
        return rollList;
    }

    public void addRollToList(int roll){
        this.rollList.add(roll);
    }

    public void undo(){
        // removes most recent saved roll if there is one
        if(rollList.size()!= 0)rollList.remove(rollList.size()-1);

    }

}
