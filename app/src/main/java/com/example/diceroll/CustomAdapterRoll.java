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

public class CustomAdapterRoll extends ArrayAdapter {
    // custom adapter for the rolls list
    private ArrayList<Integer> rolls;
    private Context context;

    public CustomAdapterRoll(@NonNull Context context, ArrayList<Integer> rolls) {
        super(context,0,rolls);
        this.rolls = rolls;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.roll_list_item, parent,false);
        }
        int roll = rolls.get(position);
        TextView text = view.findViewById(R.id.roll);
        text.setText(String.valueOf(roll));

        return view;
    }

}
