package com.example.diceroll;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterList extends ArrayAdapter {

    private ArrayList<Game> games;
    private Context context;

    public CustomAdapterList(@NonNull Context context, ArrayList<Game> games) {
        super(context,0,games);
        this.games = games;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent,false);
        }
        Game game = games.get(position);
        TextView gameName = view.findViewById(R.id.name_text);
        gameName.setText(game.getName());
        TextView totalRolls = view.findViewById(R.id.total_text);
        totalRolls.setText(String.valueOf(game.getTotalRolls()));

        return view;
    }

}

