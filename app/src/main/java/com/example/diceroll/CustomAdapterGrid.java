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

public class CustomAdapterGrid extends ArrayAdapter {

    private final ArrayList<String> numbers;
    private final Context context;

    public CustomAdapterGrid(@NonNull Context context, ArrayList<String> numbers) {
        super(context,0,numbers);
        this.numbers = numbers;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.grid_item, parent,false);
        }
        String number = numbers.get(position);
        TextView name = view.findViewById(R.id.number_text);
        name.setText(number);

        return view;
    }

}

