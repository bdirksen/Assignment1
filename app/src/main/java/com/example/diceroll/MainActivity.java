package com.example.diceroll;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.diceroll.db.AppDataBase;
import com.example.diceroll.db.Game;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddGameFragment.OnFragmentInteractionListener {
    ListView gameList;
    CustomAdapterList gamesAdapter;
    ArrayList<Game> gamesDataList;
    int removeIndex;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameList = findViewById(R.id.games_list);
        gameList.setClickable(true);


        gamesAdapter = new CustomAdapterList(this, gamesDataList);
        gameList.setAdapter(gamesAdapter);

        loadGameList();

        //gamesDataList.add(new Game("test",3,6 )); // testing game


        ActivityResultLauncher<Intent> gameActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Game game = (Game) data.getSerializableExtra("game");
                            boolean delete = data.getBooleanExtra("delete",false);
                            gamesDataList.remove(removeIndex);
                            if(!delete)gamesDataList.add(removeIndex,game);
                            gamesAdapter.notifyDataSetChanged();
                        }
                    }
                });
        gameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                intent.putExtra("game", gamesDataList.get(i));
                removeIndex = i;
                gameActivityResultLauncher.launch(intent);

            }
        });


        final FloatingActionButton addGameButton = findViewById(R.id.add_game);
        addGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddGameFragment().show(getSupportFragmentManager(),"ADD_GAME");
            }
        });

    }

    @Override
    public void onOkPressed(Game newGame) {
        AppDataBase db = AppDataBase.getDbInstance(this.getApplicationContext());
        db.gameDao().insertGame(newGame);
        gamesDataList.add(newGame);
        loadGameList();
    }

    private void loadGameList(){
        AppDataBase db = AppDataBase.getDbInstance(this.getApplicationContext());
        gamesDataList = db.gameDao().getAllGames();
        gamesAdapter.notifyDataSetChanged();

    }

}