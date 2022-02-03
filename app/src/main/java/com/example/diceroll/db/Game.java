package com.example.diceroll.db;

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
    public String name;
    @ColumnInfo(name = "rolls")
    public int rolls;
    @ColumnInfo(name = "sides")
    public int sides;
    @ColumnInfo(name = "rollList")
    public ArrayList<Integer> rollList = new ArrayList<>();

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
