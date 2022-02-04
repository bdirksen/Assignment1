package com.example.diceroll;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class Game implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int gid;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "rolls")
    private int rolls;
    @ColumnInfo(name = "sides")
    private int sides;
    @ColumnInfo(name = "rollList")
    private ArrayList<Integer> rollList = new ArrayList<>();
    @ColumnInfo(name = "totalRolls")
    private int totalRolls;

    public Game(String name, int rolls, int sides,String date){
        this.name = name;
        this.rolls = rolls;
        this.sides = sides;
        this.date = date;
        this.totalRolls = 0;
    }

    public void setTotalRolls(int totalRolls) {
        this.totalRolls = totalRolls;
    }

    public int getTotalRolls() {
        return totalRolls;
    }

    public String getDate() {
        return date;
    }

    public void setRollList(ArrayList<Integer> rollList) {
        this.rollList = rollList;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRolls(int rolls) {
        this.rolls = rolls;
    }

    public void setSides(int sides) {
        this.sides = sides;
    }

    public ArrayList<Integer> getRollList() {
        return rollList;
    }

    public void addRollToList(int roll){
        this.rollList.add(roll);
        this.totalRolls++;
    }

    public void undo(){
        // removes most recent saved roll if there is one
        if(rollList.size()!= 0)rollList.remove(rollList.size()-1);

    }

}
