package com.example.diceroll;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;


public class AddGameFragment extends DialogFragment {
    private EditText gameName;
    private EditText gameRolls;
    private EditText gameSides;
    private EditText gameDay;
    private EditText gameMonth;
    private EditText gameYear;
    private OnFragmentInteractionListener listener;
    private final Calendar calendar = Calendar.getInstance();

    public interface OnFragmentInteractionListener{
        void onOkPressed(Game newGame);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener){
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.add_game_fragment_layout, null);
        gameName = view.findViewById(R.id.game_name_editText);
        gameRolls = view.findViewById(R.id.game_rolls_editText);
        gameSides = view.findViewById(R.id.game_sides_editText);
        gameDay = view.findViewById(R.id.game_day_editText);
        gameMonth = view.findViewById(R.id.game_month_editText);
        gameYear = view.findViewById(R.id.game_year_editText);
        gameDay.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        gameMonth.setText(String.valueOf(calendar.get(Calendar.MONTH)+1));
        gameYear.setText(String.valueOf(calendar.get(Calendar.YEAR)));

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add Game")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    String name = gameName.getText().toString();
                    String rollsString = gameRolls.getText().toString();
                    String sidesString = gameSides.getText().toString();
                    String day = gameDay.getText().toString();
                    String month = gameMonth.getText().toString();
                    String year = gameYear.getText().toString();
                    String date = year + "-" + month + "-" + day;

                    try{
                        int rolls = Integer.parseInt(rollsString);
                        int sides = Integer.parseInt(sidesString);
                        if(rolls < 4 && rolls > 0)
                            if(sides < 7 && sides > 0){
                                listener.onOkPressed(new Game(name, rolls, sides, date));
                            }
                    }catch(Exception ignored){
                    }



                }).create();

    }
}
