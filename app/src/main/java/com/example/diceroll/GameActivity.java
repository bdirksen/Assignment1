package com.example.diceroll;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class GameActivity extends AppCompatActivity {

    // needed variables
    GridView numberGrid;
    ArrayAdapter<String> numbersAdapter;
    ArrayList<String> numbersDataList;
    ListView rollList;
    ArrayAdapter<Integer> rollsAdapter;
    String name;
    int rolls;
    int sides;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        //variables for the grid selection display
        numberGrid = findViewById(R.id.gridView);
        numberGrid.setClickable(true);
        numbersDataList = new ArrayList<>();

        // retrieving selected game class data from the intent and setting variables
        Game game = (Game) getIntent().getSerializableExtra("game");
        name = game.getName();
        rolls = game.getRolls();
        sides = game.getSides();

        // sets the title at top of activity to the name of the game
        TextView nameTextView = findViewById(R.id.textView_gameName);
        nameTextView.setText(name.toUpperCase(Locale.ROOT));

        // adds each different possible roll number to the arraylist
        for(int i = rolls; i < rolls*sides+1; i++){
            numbersDataList.add(String.valueOf(i));
        }

        // configuring the adapter
        numbersAdapter = new CustomAdapterGrid(this,numbersDataList);
        numberGrid.setAdapter(numbersAdapter);

        // handling the selection of the roll
        numberGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                game.addRollToList(Integer.valueOf(numbersDataList.get(i)));
                rollsAdapter.notifyDataSetChanged();
            }
        });

        // variables for the rolls display
        rollList = findViewById(R.id.rollList);
        rollsAdapter = new CustomAdapterRoll(this, game.getRollList());
        rollList.setAdapter(rollsAdapter);

        // delete game button and handling
        Button delete = findViewById(R.id.gameDelete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameActivity.this,MainActivity.class);
                intent.putExtra("game", game);
                intent.putExtra("delete",true);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        // back button and handling
        Button back = findViewById(R.id.back_button);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameActivity.this,MainActivity.class);
                intent.putExtra("game", game);
                intent.putExtra("delete",false);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        // undo most recent roll button and handling
        Button undo = findViewById(R.id.undo_button);

        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.undo();
                rollsAdapter.notifyDataSetChanged();
            }
        });

        // stats button and handling
        Button stats = findViewById(R.id.stats_button);

        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent statsIntent = new Intent(GameActivity.this,StatsActivity.class);
                statsIntent.putExtra("game", game);
                // starts stats activity to display roll data
                startActivity(statsIntent);
            }
        });
    }
}