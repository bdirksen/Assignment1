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

public class CustomAdapterStatsList extends ArrayAdapter {

    private ArrayList<Stat> stats;
    private Context context;

    public CustomAdapterStatsList(@NonNull Context context, ArrayList<Stat> stats) {
        super(context,0,stats);
        this.stats = stats;
        this.context = context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.stats_list_item, parent,false);
        }
        Stat stat = stats.get(position);
        // setting up the graph
        TextView rolls = view.findViewById(R.id.rollNumber_textView);
        TextView amount = view.findViewById(R.id.rollAmount_textView);
        String rollsOut = stat.getRoll() + " ("+stat.getAmount()+") ";
        rolls.setText(rollsOut);
        int amountOfHash = Integer.parseInt(stat.getAmount())/5;
        String amountOut = "| ";
        for(int i=0; i < amountOfHash;i++) amountOut += "#";
        amount.setText(amountOut);



        return view;
    }

}
