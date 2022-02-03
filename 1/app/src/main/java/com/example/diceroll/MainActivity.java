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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddGameFragment.OnFragmentInteractionListener {
    ListView gameList;
    ArrayAdapter<Game> gamesAdapter;
    ArrayList<Game> gamesDataList;
    int removeIndex;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameList = findViewById(R.id.games_list);
        gameList.setClickable(true);

        gamesDataList = new ArrayList<>();
        gamesDataList.add(new Game("test",3,6 )); // testing game
        gamesAdapter = new CustomAdapterList(this, gamesDataList);
        gameList.setAdapter(gamesAdapter);

        ActivityResultLauncher<Intent> gameActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Game game = (Game) data.getSerializableExtra("game");
                            Boolean delete = data.getBooleanExtra("delete",false);
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
        gamesAdapter.add(newGame);
    }

}