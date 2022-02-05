package com.example.diceroll;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.diceroll.db.AppDataBase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddGameFragment.OnFragmentInteractionListener {
    private ListView gameList;
    private CustomAdapterList gamesAdapter;
    private ArrayList<Game> gamesDataList;
    private int removeIndex;
    private AppDataBase db;



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameList = findViewById(R.id.games_list);
        gameList.setClickable(true);

        gamesDataList = new ArrayList<>();

        db = AppDataBase.getDbInstance(this.getApplicationContext());
        gamesDataList = (ArrayList<Game>) db.gameDao().getAllGames();

        //gamesDataList.add(new Game("test",3,6 )); // testing game
        gamesAdapter = new CustomAdapterList(this, gamesDataList);
        gameList.setAdapter(gamesAdapter);

        ActivityResultLauncher<Intent> gameActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        assert data != null;
                        Game game = (Game) data.getSerializableExtra("game");
                        boolean delete = data.getBooleanExtra("delete",false);
                        if(!delete){
                            refreshGame(game);
                        }else{
                            removeGame();
                        }
                        gamesAdapter.notifyDataSetChanged();
                    }
                });
        gameList.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
            intent.putExtra("game", gamesDataList.get(i));
            removeIndex = i;
            gameActivityResultLauncher.launch(intent);

        });


        final FloatingActionButton addGameButton = findViewById(R.id.add_game);
        addGameButton.setOnClickListener(view -> new AddGameFragment().show(getSupportFragmentManager(),"ADD_GAME"));

    }

    @Override
    public void onOkPressed(Game newGame) {
        db.gameDao().insertGame(newGame);
        gamesDataList.add(newGame);

    }
    private void refreshGame(Game newGame){
        removeGame();
        db.gameDao().insertGame(newGame);
        gamesDataList.add(removeIndex, newGame);
    }

    private void removeGame(){
        db.gameDao().deleteGame(gamesDataList.get(removeIndex));
        gamesDataList.remove(removeIndex);
    }
}