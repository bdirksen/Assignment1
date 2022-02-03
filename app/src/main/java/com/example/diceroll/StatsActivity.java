package com.example.diceroll;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.diceroll.db.Game;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {

    ListView statsList;
    ArrayAdapter<Stat> statsAdapter;
    ArrayList<Stat> statsDataList;
    Game game;
    String name;
    int rolls;
    int sides;
    ArrayList<Integer> rollList;
    int min;
    int max = 0;
    int average;
    int total = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        // setting variables
        game = (Game) getIntent().getSerializableExtra("game");
        name = game.getName();
        rolls = game.getRolls();
        sides = game.getSides();
        rollList = game.getRollList();
        statsList = findViewById(R.id.stats_ListView);
        statsDataList = new ArrayList<>();

        // makes a item for every unique roll number and finds the amount of those rolls
        if(rollList.size() != 0) min = rollList.get(0);
        for(int i = rolls; i < rolls*sides+1; i++){
            int amount = 0;
            for(int f = 0; f < rollList.size(); f++){
                if(rollList.get(f) == i) amount++;
            }
            if(amount < min) min = amount;
            if(amount > max) max = amount;
            total += amount;
            statsDataList.add(new Stat(String.valueOf(amount), String.valueOf(i)));
        }
        average = total / (rolls*sides-rolls+1);

        // sets up stats display
        TextView minView = findViewById(R.id.min_textView);
        TextView maxView = findViewById(R.id.max_textView);
        TextView averageView = findViewById(R.id.average_textView);
        TextView totalView = findViewById(R.id.total_textView);
        minView.setText(" Min: "+ min);
        maxView.setText(" Max: "+ max);
        averageView.setText(" Average: "+ average);
        totalView.setText(" Total: "+ total);

        // sets up adapter
        statsAdapter = new CustomAdapterStatsList(this, statsDataList);
        statsList.setAdapter(statsAdapter);



        //button to go back to previous page
        Button statsButton = findViewById(R.id.stats_back_button);

        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}