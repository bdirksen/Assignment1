package com.example.diceroll;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class GameActivity extends AppCompatActivity implements EditGameFragment.OnFragmentInteractionListener{

    // needed variables
    GridView numberGrid;
    CustomAdapterGrid numbersAdapter;
    ArrayList<String> numbersDataList;
    ListView rollList;
    CustomAdapterRoll rollsAdapter;
    String name;
    int rolls;
    int sides;
    String date;
    Game game;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        //variables for the grid selection display
        numberGrid = findViewById(R.id.gridView);
        numberGrid.setClickable(true);
        numbersDataList = new ArrayList<>();

        // retrieving selected game class data from the intent and setting variables
        game = (Game) getIntent().getSerializableExtra("game");
        name = game.getName();
        rolls = game.getRolls();
        sides = game.getSides();
        date = game.getDate();

        // sets the title at top of activity to the name of the game
        TextView nameTextView = findViewById(R.id.textView_gameName);
        nameTextView.setText(name);

        // lets u edit the game name
        Button edit = findViewById(R.id.game_edit_button);
        edit.setOnClickListener(view -> new EditGameFragment(game).show(getSupportFragmentManager(),"ADD_GAME"));

        // sets the date at the top of the activity
        TextView dateTextView = findViewById(R.id.textView2);
        dateTextView.setText("Date: "+date);

        // adds each different possible roll number to the arraylist
        for(int i = rolls; i < rolls*sides+1; i++){
            numbersDataList.add(String.valueOf(i));
        }

        // configuring the adapter
        numbersAdapter = new CustomAdapterGrid(this,numbersDataList);
        numberGrid.setAdapter(numbersAdapter);

        // handling the selection of the roll
        numberGrid.setOnItemClickListener((adapterView, view, i, l) -> {
            game.addRollToList(Integer.parseInt(numbersDataList.get(i)));
            rollsAdapter.notifyDataSetChanged();
        });

        // variables for the rolls display
        rollList = findViewById(R.id.rollList);
        rollsAdapter = new CustomAdapterRoll(this, game.getRollList());
        rollList.setAdapter(rollsAdapter);

        // delete game button and handling
        Button delete = findViewById(R.id.gameDelete);

        delete.setOnClickListener(view -> {
            Intent intent = new Intent(GameActivity.this,MainActivity.class);
            intent.putExtra("game", game);
            intent.putExtra("delete",true);
            setResult(RESULT_OK,intent);
            finish();
        });

        // back button and handling
        Button back = findViewById(R.id.back_button);

        back.setOnClickListener(view -> {
            Intent intent = new Intent(GameActivity.this,MainActivity.class);
            intent.putExtra("game", game);
            intent.putExtra("delete",false);
            setResult(RESULT_OK,intent);
            finish();
        });

        // undo most recent roll button and handling
        Button undo = findViewById(R.id.undo_button);

        undo.setOnClickListener(view -> {
            game.undo();
            rollsAdapter.notifyDataSetChanged();
        });

        // stats button and handling
        Button stats = findViewById(R.id.stats_button);

        stats.setOnClickListener(view -> {
            Intent statsIntent = new Intent(GameActivity.this,StatsActivity.class);
            statsIntent.putExtra("game", game);
            // starts stats activity to display roll data
            startActivity(statsIntent);
        });

    }
    @Override
    public void onOkPressedEdit(String name,int rolls,int sides,String date,ArrayList<Integer> rollList,int total) {
        game.setName(name);
        game.setRolls(rolls);
        game.setSides(sides);
        game.setTotalRolls(total);
        game.setRollList(rollList);
        game.setDate(date);
        Intent intent = new Intent(GameActivity.this,MainActivity.class);
        intent.putExtra("game", game);
        intent.putExtra("delete",false);
        setResult(RESULT_OK,intent);
        finish();
    }
}